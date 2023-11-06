/*
 Navicat Premium Data Transfer

 Source Server         : HL
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : wj_api

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 06/11/2023 22:49:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for interface_info
-- ----------------------------
DROP TABLE IF EXISTS `interface_info`;
CREATE TABLE `interface_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `description` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '描述',
  `url` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '接口地址',
  `requestParams` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求参数',
  `requestHeader` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '请求头',
  `responseHeader` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '响应头',
  `status` int NOT NULL DEFAULT 0 COMMENT '接口状态（0-关闭，1-开启）',
  `method` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '请求类型',
  `userId` bigint NOT NULL COMMENT '创建人',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除(0-未删, 1-已删)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '接口信息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of interface_info
-- ----------------------------
INSERT INTO `interface_info` VALUES (1, '许擎宇', '薛聪健', 'www.cary-king.net', NULL, '潘博涛', '谭聪健', 1, '石炫明', 9500534531, '2023-11-06 21:21:39', '2023-11-06 21:26:35', 0);
INSERT INTO `interface_info` VALUES (2, '陆弘文', '白志强', 'www.leslee-kuhn.net', NULL, '潘懿轩', '马鸿涛', 0, '陈峻熙', 3982575846, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (3, '毛建辉', '罗文', 'www.rosaria-kilback.io', NULL, '冯子默', '彭哲瀚', 0, '赵远航', 121776355, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (4, '彭雨泽', '蔡煜祺', 'www.norris-bergstrom.biz', NULL, '董思源', '田晓博', 0, '潘擎宇', 740, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (5, '傅志强', '陈梓晨', 'www.jordan-reinger.com', NULL, '金志强', '熊锦程', 0, '邓睿渊', 35542559, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (6, '吕黎昕', '孔越彬', 'www.fe-okon.info', NULL, '万伟宸', '林昊然', 0, '孟荣轩', 1445, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (7, '夏雪松', '许子骞', 'www.lashawna-legros.co', NULL, '蔡昊然', '胡鹏涛', 0, '钟立辉', 34075514, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (8, '严钰轩', '阎志泽', 'www.kay-funk.biz', NULL, '莫皓轩', '郭黎昕', 0, '龚天宇', 70956, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (9, '萧嘉懿', '曹熠彤', 'www.margarette-lindgren.biz', NULL, '田泽洋', '邓睿渊', 0, '梁志强', 98, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (10, '杜驰', '冯思源', 'www.vashti-auer.org', NULL, '黎健柏', '武博文', 0, '李伟宸', 9, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (11, '史金鑫', '蔡鹏涛', 'www.diann-keebler.org', NULL, '徐烨霖', '阎建辉', 0, '李烨伟', 125, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (12, '林炫明', '贾旭尧', 'www.dotty-kuvalis.io', NULL, '梁雨泽', '龙伟泽', 0, '许智渊', 79998, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (13, '何钰轩', '赖智宸', 'www.andy-adams.net', NULL, '崔思淼', '白鸿煊', 0, '邵振家', 7167482751, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (14, '魏志强', '于立诚', 'www.ione-aufderhar.biz', NULL, '朱懿轩', '万智渊', 0, '唐昊强', 741098, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (15, '严君浩', '金胤祥', 'www.duane-boyle.org', NULL, '雷昊焱', '侯思聪', 0, '郝思', 580514, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (16, '姚皓轩', '金鹏', 'www.lyda-klein.biz', NULL, '杜昊强', '邵志泽', 0, '冯鸿涛', 6546, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (17, '廖驰', '沈泽洋', 'www.consuelo-sipes.info', NULL, '彭昊然', '邓耀杰', 0, '周彬', 7761037, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (18, '赖智渊', '邓志泽', 'www.emerson-mann.co', NULL, '熊明哲', '贺哲瀚', 0, '田鹏', 381422, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (19, '许涛', '陆致远', 'www.vella-ankunding.name', NULL, '贾哲瀚', '莫昊焱', 0, '袁越彬', 4218096, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (20, '吕峻熙', '沈鹏飞', 'www.shari-reichel.org', NULL, '郭鸿煊', '覃烨霖', 0, '熊黎昕', 493, '2023-11-06 21:21:39', '2023-11-06 21:21:39', 0);
INSERT INTO `interface_info` VALUES (21, '获取用户名', '得到用户名字', 'http://localhost:8080/api/name/user', NULL, NULL, NULL, 1, 'post', 2, '2023-11-06 22:35:55', '2023-11-06 22:38:20', 0);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userName` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `userAccount` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `userAvatar` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `gender` tinyint NULL DEFAULT NULL COMMENT '性别',
  `userRole` varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user / admin',
  `userPassword` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `accessKey` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'accessKey',
  `secretKey` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'secretKey',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uni_userAccount`(`userAccount` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, NULL, 'user', NULL, NULL, 'user', 'bc120b04d97f470fa371a8ab94eef6c1', 'cli_779740f2ea4f2bb1579bdeeb2312c827', '75cea1453f1a538fac1a9028be86758a', '2023-11-06 21:22:53', '2023-11-06 21:22:53', 0);
INSERT INTO `user` VALUES (2, NULL, 'admin', NULL, NULL, 'admin', 'bc120b04d97f470fa371a8ab94eef6c1', 'cli_c7e1c586a3c9937d77f5ba0286f005fd', '456a0463519054b9094a9356bfad68c9', '2023-11-06 21:23:01', '2023-11-06 21:23:20', 0);

-- ----------------------------
-- Table structure for user_interface_info
-- ----------------------------
DROP TABLE IF EXISTS `user_interface_info`;
CREATE TABLE `user_interface_info`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userId` bigint NOT NULL COMMENT '调用用户 id',
  `interfaceInfoId` bigint NOT NULL COMMENT '接口 id',
  `totalNum` int NOT NULL DEFAULT 0 COMMENT '总调用次数',
  `leftNum` int NOT NULL DEFAULT 0 COMMENT '剩余调用次数',
  `status` int NOT NULL DEFAULT 0 COMMENT '0-正常，1-禁用',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint NOT NULL DEFAULT 0 COMMENT '是否删除(0-未删, 1-已删)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户调用接口关系' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_interface_info
-- ----------------------------
INSERT INTO `user_interface_info` VALUES (1, 2, 21, 10, 5, 0, '2023-11-06 22:45:09', '2023-11-06 22:47:30', 0);

SET FOREIGN_KEY_CHECKS = 1;
