/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : sc_gateway

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 14/04/2022 14:54:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gateway_route
-- ----------------------------
DROP TABLE IF EXISTS `gateway_route`;
CREATE TABLE `gateway_route` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `route_id` varchar(100) NOT NULL COMMENT '路由id',
  `uri` varchar(100) NOT NULL COMMENT 'uri路径',
  `predicates` text NOT NULL COMMENT '判定器',
  `filters` text COMMENT '过滤器',
  `orders` int DEFAULT NULL COMMENT '排序',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `status` varchar(1) DEFAULT 'Y' COMMENT '状态：Y-有效，N-无效',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `deleted` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_gateway_routes_uri` (`uri`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='网关路由表';

-- ----------------------------
-- Records of gateway_route
-- ----------------------------
BEGIN;
INSERT INTO `gateway_route` (`id`, `route_id`, `uri`, `predicates`, `filters`, `orders`, `description`, `status`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (101, 'authorization-server', 'lb://authorization-server:8000', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/authorization-server/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', 100, '授权认证服务网关注册', 'Y', '2022-04-07 14:13:11', '2022-04-07 14:13:11', 'system', 'system', 'N');
INSERT INTO `gateway_route` (`id`, `route_id`, `uri`, `predicates`, `filters`, `orders`, `description`, `status`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (102, 'authentication-server', 'lb://authentication-server:8001', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/authentication-server/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', 100, '签权服务网关注册', 'Y', '2022-04-07 14:13:11', '2022-04-07 14:13:11', 'system', 'system', 'N');
INSERT INTO `gateway_route` (`id`, `route_id`, `uri`, `predicates`, `filters`, `orders`, `description`, `status`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (103, 'organization', 'lb://organization:8010', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/organization/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', 100, '系统管理相关接口', 'Y', '2022-04-07 14:13:11', '2022-04-07 14:13:11', 'system', 'system', 'N');
INSERT INTO `gateway_route` (`id`, `route_id`, `uri`, `predicates`, `filters`, `orders`, `description`, `status`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (104, 'gateway-admin', 'lb://gateway-admin:8445', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/gateway-admin/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', 100, '网关管理相关接口', 'Y', '2022-04-07 14:13:11', '2022-04-07 14:13:11', 'system', 'system', 'N');
INSERT INTO `gateway_route` (`id`, `route_id`, `uri`, `predicates`, `filters`, `orders`, `description`, `status`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (105, 'product', 'lb://product:9002', '[{\"name\":\"Path\",\"args\":{\"pattern\":\"/product/**\"}}]', '[{\"name\":\"StripPrefix\",\"args\":{\"parts\":\"1\"}}]', 100, '产品管理接口', 'Y', '2022-04-08 16:27:08', '2022-04-08 16:27:08', 'sysadmin', 'sysadmin', 'N');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
