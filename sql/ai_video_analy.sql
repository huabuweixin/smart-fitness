/*
 Navicat Premium Dump SQL

 Source Server         : ruoyi-vue-blog
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : localhost:3306
 Source Schema         : ruoyi-vue-blog

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 16/05/2025 20:57:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ai_video_analy
-- ----------------------------
DROP TABLE IF EXISTS `ai_video_analy`;
CREATE TABLE `ai_video_analy`  (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `original_path` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `processed_path` varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `user_id` bigint NULL DEFAULT NULL,
  `mode` enum('BEGINNER','PRO') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL,
  `status` enum('PENDING','PROCESSING','COMPLETED','FAILED') CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT 'PENDING',
  `created_at` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `analysis_result` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
