/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : ncloud

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 14/04/2022 14:45:46
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` varchar(20) NOT NULL COMMENT '父菜单id',
  `type` varchar(100) DEFAULT NULL COMMENT '菜单类型',
  `href` varchar(200) DEFAULT NULL COMMENT '菜单路径',
  `icon` varchar(200) DEFAULT NULL COMMENT '菜单图标',
  `name` varchar(200) DEFAULT NULL COMMENT '菜单名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `order_num` int DEFAULT NULL COMMENT '创建时间',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `deleted` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单表';

-- ----------------------------
-- Records of menu
-- ----------------------------
BEGIN;
INSERT INTO `menu` (`id`, `parent_id`, `type`, `href`, `icon`, `name`, `description`, `order_num`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (101, '-1', 'MENU', '/admin', 'setting', '系统管理', '系统设置管理', 0, '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `menu` (`id`, `parent_id`, `type`, `href`, `icon`, `name`, `description`, `order_num`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (102, '101', 'MENU', '/admin/users', 'fa-user', '用户管理', '用户新增，修改，查看，删除', 10, '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `menu` (`id`, `parent_id`, `type`, `href`, `icon`, `name`, `description`, `order_num`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (103, '101', 'MENU', '/admin/menus', 'category', '菜单管理', '菜单新增，修改，删除', 20, '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
COMMIT;

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(200) DEFAULT NULL COMMENT '岗位名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `deleted` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='岗位表';

-- ----------------------------
-- Records of position
-- ----------------------------
BEGIN;
INSERT INTO `position` (`id`, `name`, `description`, `deleted`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (101, '首席执行官', '公司CEO，负责公司整体运转', 'N', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `position` (`id`, `name`, `description`, `deleted`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (102, '首席运营官', '公司COO，负责公司整体运营', 'N', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `position` (`id`, `name`, `description`, `deleted`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (103, '首席技术官', '公司CTO，负责公司整体运营', 'N', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
COMMIT;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `code` varchar(100) NOT NULL COMMENT '资源code',
  `type` varchar(100) NOT NULL COMMENT '资源类型',
  `name` varchar(200) NOT NULL COMMENT '资源名称',
  `url` varchar(200) NOT NULL COMMENT '资源url',
  `method` varchar(20) NOT NULL COMMENT '资源方法',
  `description` varchar(500) DEFAULT NULL COMMENT '简介',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `deleted` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_resource_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=322 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='资源表';

-- ----------------------------
-- Records of resource
-- ----------------------------
BEGIN;
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (101, 'user_manager:btn_add', 'user', '新增用户', '/user', 'POST', '新增用户功能', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (102, 'user_manager:btn_edit', 'user', '编辑用户', '/user/{id}', 'PUT', '编辑用户功能', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (103, 'user_manager:btn_del', 'user', '删除用户', '/user/{id}', 'DELETE', '根据用户id删除用户', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (104, 'user_manager:view', 'user', '查看用户', '/user/{id}', 'GET', '根据用户id获取用户', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (105, 'user_manager:query', 'user', '搜索用户', '/user/conditions', 'POST', '根据条件查询用户', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (106, 'user_manager:get', 'user', '获取用户', '/user', 'GET', '根据唯一标识获取用户', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (201, 'role_manager:btn_add', 'role', '新增角色', '/role', 'POST', '新增角色功能', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (202, 'role_manager:btn_edit', 'role', '编辑角色', '/role/{id}', 'PUT', '编辑角色功能', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (203, 'role_manager:btn_del', 'role', '删除角色', '/role/{id}', 'DELETE', '根据id删除角色', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (204, 'role_manager:view', 'role', '查看角色', '/role/{id}', 'GET', '根据id获取角色', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (205, 'role_manager:user', 'role', '根据用户id查询角色', '/role/user/{userId}', 'GET', '根据用户id获取用户所拥有的角色集', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (206, 'role_manager:all', 'role', '获取所有角色', '/role/all', 'GET', '获取所有角色', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (207, 'role_manager:query', 'role', '搜索角色', '/role/conditions', 'POST', '根据条件查询角色', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (301, 'group_manager:parent', 'group', '根据父id查询组', '/group/parent/{id}', 'GET', '根据父id查询用户组', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (302, 'group_manager:get', 'group', '查看用户组', '/group/{id}', 'GET', '根据id查询用户组', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (303, 'group_manager:query', 'group', '搜索用户组', '/group/conditions', 'POST', '根据条件查询用户组信息', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (304, 'group_manager:del', 'group', '删除用户组', '/group/{id}', 'DELETE', '根据用户id删除用户组', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (305, 'group_manager:edit', 'group', '编辑用户组', '/group/{id}', 'PUT', '修改用户组', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (306, 'group_manager:add', 'group', '新增用户组', '/group', 'POST', '新增用户组', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (307, 'gateway_manager:add', 'gateway', '新增网关路由', '/gateway/routes', 'POST', '新增网关路由', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (308, 'gateway_manager:edit', 'gateway', '修改网关路由', '/gateway/routes/{id}', 'PUT', '修改网关路由', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (309, 'gateway_manager:adel', 'gateway', '删除网关路由', '/gateway/routes/{id}', 'DELETE', '删除网关路由', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (310, 'gateway_manager:view', 'gateway', '查看网关路由', '/gateway/routes/{id}', 'GET', '查看网关路由', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (311, 'gateway_manager:query', 'gateway', '搜索网关路由', '/gateway/routes/conditions', 'POST', '搜索网关路由', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (312, 'gateway_manager:overload', 'gateway', '全局加载路由', '/gateway/routes/overload', 'POST', '全局加载路由', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (313, 'resource_manager:add', 'resource', '新增网关路由', '/resource', 'POST', '新增资源路由', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (314, 'resource_manager:edit', 'resource', '修改网关路由', '/resource/{id}', 'PUT', '修改资源', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (315, 'resource_manager:adel', 'resource', '删除网关路由', '/resource/{id}', 'DELETE', '删除资源', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (316, 'resource_manager:view', 'resource', '查看网关路由', '/resource/{id}', 'GET', '查看资源', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (317, 'resource_manager:query', 'resource', '搜索网关路由', '/resource/conditions', 'POST', '搜索资源', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (318, 'resource_manager:all', 'resource', '全局加载路由', '/resource/all', 'GET', '查询全部资源', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (319, 'position_manager:btn_add', 'position', '添加职位', '/position', 'POST', '添加职位功能', '2022-04-08 14:29:32', '2022-04-08 14:29:32', 'sysadmin', 'sysadmin', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (320, 'position_manager:btn_edit', 'position', '编辑职位', '/position/{id}', 'PUT', '编辑职位功能', '2022-04-08 14:39:06', '2022-04-08 14:39:06', 'sysadmin', 'sysadmin', 'N');
INSERT INTO `resource` (`id`, `code`, `type`, `name`, `url`, `method`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (321, 'position_manager:btn_del', 'position', '删除职位', '/position/{id}', 'DELETE', '删除职位功能', '2022-04-08 14:42:03', '2022-04-08 14:42:03', 'sysadmin', 'sysadmin', 'N');
COMMIT;

-- ----------------------------
-- Table structure for role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `role_menu_relation`;
CREATE TABLE `role_menu_relation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `menu_id` varchar(20) NOT NULL COMMENT '菜单id',
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关系表';

-- ----------------------------
-- Records of role_menu_relation
-- ----------------------------
BEGIN;
INSERT INTO `role_menu_relation` (`id`, `menu_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (101, '101', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_menu_relation` (`id`, `menu_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (102, '102', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_menu_relation` (`id`, `menu_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (103, '103', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_menu_relation` (`id`, `menu_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (104, '101', '102', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_menu_relation` (`id`, `menu_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (105, '102', '102', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_menu_relation` (`id`, `menu_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (106, '101', '103', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_menu_relation` (`id`, `menu_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (107, '102', '103', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_menu_relation` (`id`, `menu_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (108, '103', '103', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
COMMIT;

-- ----------------------------
-- Table structure for role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `role_resource_relation`;
CREATE TABLE `role_resource_relation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '关系id',
  `resource_id` varchar(20) NOT NULL COMMENT '角色id',
  `role_id` varchar(20) NOT NULL COMMENT '资源id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和资源关系表';

-- ----------------------------
-- Records of role_resource_relation
-- ----------------------------
BEGIN;
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (101, '101', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (102, '102', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (103, '103', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (104, '104', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (105, '105', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (106, '106', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (201, '201', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (202, '202', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (203, '203', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (204, '204', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (205, '205', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (206, '206', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (207, '207', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (208, '301', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (209, '302', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (210, '303', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (211, '304', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (212, '305', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (213, '306', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (401, '307', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (402, '308', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (403, '309', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (404, '310', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (405, '311', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (406, '312', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (501, '313', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (502, '314', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (503, '315', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (504, '316', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (505, '317', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `role_resource_relation` (`id`, `resource_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (506, '318', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `code` varchar(100) NOT NULL COMMENT '角色code',
  `name` varchar(200) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(500) DEFAULT NULL COMMENT '简介',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  `deleted` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` (`id`, `code`, `name`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (101, 'ADMIN', '超级管理员', '公司IT总负责人', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `roles` (`id`, `code`, `name`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (102, 'FIN', '财务', '财务', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
INSERT INTO `roles` (`id`, `code`, `name`, `description`, `created_time`, `updated_time`, `created_by`, `updated_by`, `deleted`) VALUES (103, 'IT', 'IT', 'IT角色', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system', 'N');
COMMIT;

-- ----------------------------
-- Table structure for t_groups
-- ----------------------------
DROP TABLE IF EXISTS `t_groups`;
CREATE TABLE `t_groups` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` varchar(20) NOT NULL COMMENT '用户组父id',
  `name` varchar(200) DEFAULT NULL COMMENT '用户组名称',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `deleted` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户组表';

-- ----------------------------
-- Records of t_groups
-- ----------------------------
BEGIN;
INSERT INTO `t_groups` (`id`, `parent_id`, `name`, `description`, `deleted`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (101, '-1', '总公司', '总公司', 'N', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `t_groups` (`id`, `parent_id`, `name`, `description`, `deleted`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (102, '101', '上海分公司', '上海分公司', 'N', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `t_groups` (`id`, `parent_id`, `name`, `description`, `deleted`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (103, '102', '研发部门', '负责产品研发', 'N', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `t_groups` (`id`, `parent_id`, `name`, `description`, `deleted`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (104, '102', '产品部门', '负责产品设计', 'N', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `t_groups` (`id`, `parent_id`, `name`, `description`, `deleted`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (105, '102', '运营部门', '负责公司产品运营', 'N', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `t_groups` (`id`, `parent_id`, `name`, `description`, `deleted`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (106, '102', '销售部门', '负责公司产品销售', 'N', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `t_groups` (`id`, `parent_id`, `name`, `description`, `deleted`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (107, '101', '北京分公司', '北京分公司', 'N', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
COMMIT;

-- ----------------------------
-- Table structure for user_group_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_group_relation`;
CREATE TABLE `user_group_relation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `group_id` varchar(20) NOT NULL COMMENT '用户组id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和组关系表';

-- ----------------------------
-- Records of user_group_relation
-- ----------------------------
BEGIN;
INSERT INTO `user_group_relation` (`id`, `user_id`, `group_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (101, '101', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `user_group_relation` (`id`, `user_id`, `group_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (102, '102', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
COMMIT;

-- ----------------------------
-- Table structure for user_position_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_position_relation`;
CREATE TABLE `user_position_relation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `position_id` varchar(20) NOT NULL COMMENT '角色id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和岗位关系表';

-- ----------------------------
-- Records of user_position_relation
-- ----------------------------
BEGIN;
INSERT INTO `user_position_relation` (`id`, `user_id`, `position_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (101, '101', '103', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `user_position_relation` (`id`, `user_id`, `position_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (102, '102', '103', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
COMMIT;

-- ----------------------------
-- Table structure for user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_role_relation`;
CREATE TABLE `user_role_relation` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '关系id',
  `user_id` varchar(20) NOT NULL COMMENT '用户id',
  `role_id` varchar(20) NOT NULL COMMENT '角色id',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关系表';

-- ----------------------------
-- Records of user_role_relation
-- ----------------------------
BEGIN;
INSERT INTO `user_role_relation` (`id`, `user_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (101, '101', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `user_role_relation` (`id`, `user_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (102, '102', '101', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `user_role_relation` (`id`, `user_id`, `role_id`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (103, '102', '103', '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
COMMIT;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '用户密码密文',
  `name` varchar(200) DEFAULT NULL COMMENT '用户姓名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '用户手机',
  `description` varchar(500) DEFAULT NULL COMMENT '简介',
  `deleted` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否已删除Y：已删除，N：未删除',
  `enabled` tinyint(1) DEFAULT NULL COMMENT '是否有效用户',
  `account_non_expired` tinyint(1) DEFAULT NULL COMMENT '账号是否未过期',
  `credentials_non_expired` tinyint(1) DEFAULT NULL COMMENT '密码是否未过期',
  `account_non_locked` tinyint(1) DEFAULT NULL COMMENT '是否未锁定',
  `created_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `created_by` varchar(100) NOT NULL COMMENT '创建人',
  `updated_by` varchar(100) NOT NULL COMMENT '更新人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_users_username` (`username`),
  UNIQUE KEY `ux_users_mobile` (`mobile`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` (`id`, `username`, `password`, `name`, `mobile`, `description`, `deleted`, `enabled`, `account_non_expired`, `credentials_non_expired`, `account_non_locked`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (101, 'admin', '$2a$10$vYA9wKn/hVGOtwQw2eHiceeIGNBdfLYpDmbzHgBSVmOfHXPH4iYdS', '超级管理员', '', NULL, 'N', 1, 1, 1, 1, '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
INSERT INTO `users` (`id`, `username`, `password`, `name`, `mobile`, `description`, `deleted`, `enabled`, `account_non_expired`, `credentials_non_expired`, `account_non_locked`, `created_time`, `updated_time`, `created_by`, `updated_by`) VALUES (102, 'zhoutaoo', '$2a$10$vYA9wKn/hVGOtwQw2eHiceeIGNBdfLYpDmbzHgBSVmOfHXPH4iYdS', '周涛', '15619841000', NULL, 'N', 1, 1, 1, 1, '2022-04-07 14:08:12', '2022-04-07 14:08:12', 'system', 'system');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
