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

 Date: 10/05/2025 14:54:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for media_image
-- ----------------------------
DROP TABLE IF EXISTS `media_image`;
CREATE TABLE `media_image`  (
  `image_id` int NOT NULL AUTO_INCREMENT COMMENT '自增',
  `file_name` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '原始文件名',
  `storage_path` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '存储路径',
  `file_size` double NULL DEFAULT NULL COMMENT '文件大小（kb）',
  `file_type` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '文件类型（jpg/png等）',
  `upload_time` datetime NULL DEFAULT NULL COMMENT '上传时间',
  `uploader_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`image_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of media_image
-- ----------------------------
INSERT INTO `media_image` VALUES (1, '111', '/profile/images/2025/05/03/2.11_20250503125936A005.png', NULL, NULL, NULL, NULL);
INSERT INTO `media_image` VALUES (2, NULL, '/profile/images/2025/05/03/1.1_20250503130814A011.png', NULL, NULL, NULL, NULL);
INSERT INTO `media_image` VALUES (3, '/profile/images/2025/05/03/1.1_20250503135611A002.png', '/profile/images/2025/05/03/1.1_20250503135611A002.png', NULL, NULL, NULL, NULL);
INSERT INTO `media_image` VALUES (4, '/profile/images/2025/05/03/image-20250409154611151_20250503125024A004.png', '/profile/images/2025/05/03/image-20250409154611151_20250503125024A004.png', NULL, NULL, NULL, NULL);
INSERT INTO `media_image` VALUES (5, '11111', '/profile/images/2025/05/03/image-20250409154611151_20250503125436A001.png', NULL, NULL, NULL, NULL);
INSERT INTO `media_image` VALUES (6, '/profile/images/2025/05/03/傲慢与偏见2_20250503125544A002.jpg', '/profile/images/2025/05/03/傲慢与偏见2_20250503125544A002.jpg', NULL, NULL, NULL, NULL);
INSERT INTO `media_image` VALUES (7, '/profile/images/2025/05/03/1.11_20250503130207A006.png', '/profile/images/2025/05/03/1.11_20250503130207A006.png', NULL, NULL, NULL, NULL);
INSERT INTO `media_image` VALUES (8, '/profile/images/2025/05/03/2.111_20250503130213A007.png', '/profile/images/2025/05/03/2.111_20250503130213A007.png', NULL, NULL, NULL, NULL);
INSERT INTO `media_image` VALUES (9, '/profile/images/2025/05/03/1.2_20250503130218A008.png', '/profile/images/2025/05/03/1.2_20250503130218A008.png', NULL, NULL, NULL, NULL);
INSERT INTO `media_image` VALUES (10, '/profile/images/2025/05/03/1.11_20250503130225A009.png', '/profile/images/2025/05/03/1.11_20250503130225A009.png', NULL, NULL, NULL, NULL);
INSERT INTO `media_image` VALUES (11, '/profile/images/2025/05/03/2.111_20250503130231A010.png', '/profile/images/2025/05/03/2.111_20250503130231A010.png', NULL, NULL, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
