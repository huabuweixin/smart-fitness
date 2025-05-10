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

 Date: 10/05/2025 14:54:25
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for media_vedio
-- ----------------------------
DROP TABLE IF EXISTS `media_vedio`;
CREATE TABLE `media_vedio`  (
  `video_id` int NOT NULL AUTO_INCREMENT,
  `file_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `storage_path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `file_size` double NULL DEFAULT NULL,
  `file_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `upload_time` datetime NULL DEFAULT NULL,
  `uploader_id` int NULL DEFAULT NULL,
  `duration` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '视频时长(s)',
  PRIMARY KEY (`video_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of media_vedio
-- ----------------------------
INSERT INTO `media_vedio` VALUES (1, NULL, NULL, NULL, NULL, NULL, NULL, '57');
INSERT INTO `media_vedio` VALUES (2, NULL, NULL, NULL, NULL, NULL, NULL, '57');
INSERT INTO `media_vedio` VALUES (3, NULL, NULL, NULL, NULL, NULL, NULL, '57');
INSERT INTO `media_vedio` VALUES (4, NULL, NULL, NULL, NULL, NULL, NULL, '57');
INSERT INTO `media_vedio` VALUES (5, NULL, NULL, NULL, NULL, NULL, NULL, '57');
INSERT INTO `media_vedio` VALUES (6, NULL, NULL, NULL, NULL, NULL, NULL, '57');
INSERT INTO `media_vedio` VALUES (7, NULL, NULL, NULL, NULL, NULL, NULL, '57');
INSERT INTO `media_vedio` VALUES (8, NULL, NULL, NULL, NULL, NULL, NULL, '57');
INSERT INTO `media_vedio` VALUES (9, NULL, NULL, NULL, NULL, NULL, NULL, '57');
INSERT INTO `media_vedio` VALUES (10, NULL, NULL, NULL, NULL, NULL, NULL, '57');
INSERT INTO `media_vedio` VALUES (11, NULL, NULL, NULL, NULL, NULL, NULL, '57');
INSERT INTO `media_vedio` VALUES (12, '/profile/videos/2025/05/03/8月28日_20250503183707A001.mp4', '/profile/videos/2025/05/03/8月28日_20250503183707A001.mp4', NULL, NULL, NULL, NULL, '57');
INSERT INTO `media_vedio` VALUES (13, '/profile/videos/2025/05/03/8月29日_20250503183438A002.mp4', '/profile/videos/2025/05/03/8月29日_20250503183438A002.mp4', NULL, NULL, NULL, NULL, '57');

SET FOREIGN_KEY_CHECKS = 1;
