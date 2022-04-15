#!/usr/bin/env bash
echo "1、开发环境准备"
echo "1.1、确保安装java、maven、git并配置号环境变量"
echo "1.2、确保安装docker、docker-compose并配置环境变量"

read -r -p "开发环境准备好了吗？" envConfirm
case $envConfirm in
  [yY][eE][sS]|[yY])
  echo "继续执行"
  ;;
  [nN][oO]|[nN])
  echo "终止运行"
  exit 1
  ;;
  *)
  echo "无效输入，终止运行"
  exit 1
  ;;
esac


echo "1.3、清理当前脚本产生的容器和镜像"
docker stop nc-rabbitmq nc-redis nc-mysql nc-nacos-standalone
docker rm nc-rabbitmq nc-redis nc-mysql nc-nacos-standalone
docker image rm rabbitmq:management-alpine redis:alpine mysql:5.7 nacos/nacos-server:1.1.3


docker stop nc-authorization-server nc-authentication-server nc-organization nc-gateway-admin nc-gateway-web
docker rm nc-authorization-server nc-authentication-server nc-organization nc-gateway-admin nc-gateway-web
docker image rm siwanper/authorization-server:latest siwanper/authentication-server:latest siwanper/organization:latest siwanper/gateway-admin:latest siwanper/gateway-web:latest

echo "2、安装公共库到maven仓库"
cd common && mvn install
echo "当前路径" && pwd
cd -

echo "3、安装认证客户端到maven仓库"
cd auth/authentication-client && mvn install
echo "当前路径" && pwd
cd -

echo "4、docker-compose安装公共服务"
cd docker-compose
#docker-compose -f docker-compose.yml up -d mysql
docker-compose -f docker-compose.yml up -d redis
docker-compose -f docker-compose.yml up -d rabbitmq

#echo "5、启动配置中心和注册中心"
#docker-compose -f docker-compose.yml -f docker-compose.nacos.yml up -d nacos

echo "当前路径" && pwd
cd -

echo "6、启动组织管理（organization）相关服务"
cd sysadmin/organization
mvn package && mvn docker:build

echo "你可以立即部署组织服务的DB(../sysadmin/db)，然后继续执行"
read -r -p "组织服务的DB是否部署完成？" orgDBConfirm
case $orgDBConfirm in
  [yY][eE][sS]|[yY])
  echo "继续执行"
  ;;
  [nN][oO]|[nN])
  echo "终止运行"
  exit 1
  ;;
  *)
  echo "无效输入，终止运行"
  exit 1
  ;;
esac

echo "当前路径" && pwd
cd -

echo "运行organization服务"
cd docker-compose
docker-compose -f docker-compose.yml -f docker-compose.auth.yml up -d organization

echo "当前路径" && pwd
cd -

echo "7、启动权限管理相关服务 authorization-server 和 authentication-server"
echo "你可以立即部署权限管理服务的DB(../auth/db)，然后继续执行"
read -r -p "权限管理服务的DB是否部署完成？" authDBConfirm
case $authDBConfirm in
  [yY][eE][sS]|[yY])
  echo "继续执行"
  ;;
  [nN][oO]|[nN])
  echo "终止运行"
  exit 1
  ;;
  *)
  echo "无效输入，终止运行"
  exit 1
  ;;
esac

echo "7.1、部署授权服务"
cd auth/authorization-server
mvn package && mvn docker:build
echo "当前路径" && pwd
cd -

echo "7.2、部署认证服务"
cd auth/authentication-server
mvn package && mvn docker:build
echo "当前路径" && pwd
cd -

echo "运行authorization-server服务"
echo "运行authentication-server服务"
cd docker-compose
docker-compose -f docker-compose.yml -f docker-compose.auth.yml up -d authorization-server
docker-compose -f docker-compose.yml -f docker-compose.auth.yml up -d authentication-server
echo "当前路径" && pwd
cd -

echo "8、部署网关管理相关服务 gateway-admin 和 gateway-web"
echo "你可以立即部署网关管理服务的DB(../gateway/db)，然后继续执行"
read -r -p "网关管理服务的DB是否部署完成？" gatewayDBConfirm
case $gatewayDBConfirm in
  [yY][eE][sS]|[yY])
  echo "继续执行"
  ;;
  [nN][oO]|[nN])
  echo "终止运行"
  exit 1
  ;;
  *)
  echo "无效输入，终止运行"
  exit 1
  ;;
esac

echo "8.1、部署网关管理服务"
cd gateway/gateway-admin
mvn package && mvn docker:build
echo "当前路径" && pwd
cd -

echo "8.2、部署网关web服务"
cd gateway/gateway-web
mvn package && mvn docker:build

echo "当前路径" && pwd
cd -

echo "运行gateway-admin服务"
echo "运行gateway-web服务"
cd docker-compose
docker-compose -f docker-compose.yml -f docker-compose.spring-gateway.yml up -d gateway-admin

docker-compose -f docker-compose.yml -f docker-compose.spring-gateway.yml up -d gateway-web
echo "当前路径" && pwd
cd -

