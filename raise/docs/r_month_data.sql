/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50527
 Source Host           : localhost:3306
 Source Schema         : raise

 Target Server Type    : MySQL
 Target Server Version : 50527
 File Encoding         : 65001

 Date: 24/05/2020 21:43:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for r_month_data
-- ----------------------------
DROP TABLE IF EXISTS `r_month_data`;
CREATE TABLE `r_month_data`  (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `spmc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '商品名称',
  `zzbm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资助编码',
  `jzxm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '家长姓名',
  `bbxm` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '宝宝姓名',
  `flzr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '福利主任',
  `szxz` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在乡镇',
  `szc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在村',
  `yf` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '月份',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of r_month_data
-- ----------------------------
INSERT INTO `r_month_data` VALUES (24, '国奶扶贫差额资助(广西玉林博白）', '2107552278', '庞柳', '庞英琪', '黄倩', '水鸣', '联合', '3');
INSERT INTO `r_month_data` VALUES (25, '国奶扶贫差额资助(广西玉林博白）', '1407551496', '张欢', '梁伟铭', '黄海燕', '亚山镇', '清湖村燕垌队', '3');
INSERT INTO `r_month_data` VALUES (26, '国奶扶贫差额资助(广西玉林博白）', '507556152', '蒋法华', '蒋子皓', '万秋梦', '凤山镇', '竹围村', '3');
INSERT INTO `r_month_data` VALUES (27, '国奶扶贫差额资助(广西玉林博白）', '507556101', '刘珍凤', '黎思雨', '秋副主任', '博白英桥', '永新村', '3');
INSERT INTO `r_month_data` VALUES (28, '国奶扶贫差额资助(广西玉林博白）', '2807554181', '李洁', '杜俊辉', '李韵', '三滩镇', '良茂杜屋', '3');
INSERT INTO `r_month_data` VALUES (29, '国奶扶贫差额资助(广西玉林博白）', '1607551776', '叶林伟', '叶雨欣', '张漫', '博白县龙潭镇', '高山村后壳岭学校队', '3');
INSERT INTO `r_month_data` VALUES (30, '国奶扶贫差额资助(广西玉林博白）', '2707553326', '莫强', '莫婷婷', '周小媛', '文地黄洛村陆麻屯十六号', '黄洛村陆麻屯十六号', '3');
INSERT INTO `r_month_data` VALUES (31, '国奶扶贫差额资助(广西玉林博白）', '2807554241', '盘飞燕', '冯一萍', '李韵', '三滩镇', '建中村', '3');
INSERT INTO `r_month_data` VALUES (32, '国奶扶贫差额资助(广西玉林博白）', '2807554873', '胡家芬', '黎宸宇', '李韵', '三滩镇', '守育村潭南队', '3');
INSERT INTO `r_month_data` VALUES (33, '国奶扶贫差额资助(广西玉林博白）', '1407552224', '林燕秋', '覃锦成', '覃永新', '亚山镇', '塘肚村', '3');

SET FOREIGN_KEY_CHECKS = 1;
