/*
 Navicat Premium Dump SQL

 Source Server         : ruoyi-vue-blog
 Source Server Type    : MySQL
 Source Server Version : 80041 (8.0.41)
 Source Host           : localhost:3306
 Source Schema         : ruoyi-vue-blog

 Target Server Type    : MySQL
 Target Server Version : 80041 (8.0.41)
 File Encoding         : 65001

 Date: 26/04/2025 13:53:38
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tutorial_management
-- ----------------------------
DROP TABLE IF EXISTS `tutorial_management`;
CREATE TABLE `tutorial_management`  (
  `tutorial_id` bigint NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '标题',
  `description` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '简介',
  `content` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL COMMENT '内容',
  `coach_id` bigint NULL DEFAULT NULL COMMENT '指定教练',
  `create_time` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '创建时间',
  `cover` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '封面',
  `status` int NULL DEFAULT NULL COMMENT '上架下架状态，0为下架，1为上架。默认为0',
  `update_time` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '更新时间',
  `update_id` bigint NULL DEFAULT NULL COMMENT '更新者id',
  `view_number` bigint NULL DEFAULT NULL COMMENT '浏览量',
  `price` decimal(10, 2) NULL DEFAULT NULL COMMENT '价格',
  `cost_id` bigint NULL DEFAULT NULL COMMENT '收费功能预留字段',
  `collection_number` bigint NULL DEFAULT NULL COMMENT '收藏量',
  `category_id` bigint NULL DEFAULT NULL COMMENT '分类功能预留字段',
  PRIMARY KEY (`tutorial_id` DESC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tutorial_management
-- ----------------------------
INSERT INTO `tutorial_management` VALUES (7, '12', '11', '<h1><span style=\"color: rgb(230, 0, 0);\">刚发的工时费VS</span></h1>', NULL, '2025-04-24 14:38:17', '/dev-api/profile/upload/2025/04/24/image-20250409154611151_20250424143812A001.png', 1, '2025-04-25 13:37:15.952', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tutorial_management` VALUES (6, NULL, NULL, '<p>给第三方第三方的</p>', NULL, '2025-04-23 22:29:44', '/dev-api/profile/upload/2025/04/24/image-20250409154611151_20250424132129A004.png', 1, '2025-04-24 14:04:22.614', NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tutorial_management` VALUES (5, NULL, NULL, '<p><br></p>', NULL, '2025-04-23 21:53:15.003', NULL, 1, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `tutorial_management` VALUES (4, '11224', '1244', '<p><img src=\"/dev-api/profile/upload/2025/04/23/屏幕截图 2024-09-29 150651_20250423224701A001.png\">272427147放大发的</p>', 224, '2025-04-21 10:57:02', '/dev-api/profile/upload/2025/04/24/image-20250409154611151_20250424132430A005.png', NULL, '2025-04-24 14:04:02.973', NULL, NULL, NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
