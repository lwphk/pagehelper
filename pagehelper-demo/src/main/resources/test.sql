/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50625
 Source Host           : localhost:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 50625
 File Encoding         : 65001

 Date: 06/07/2018 10:04:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uname` varchar(320) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
BEGIN;
INSERT INTO `user` VALUES (1, 'u1', 1);
INSERT INTO `user` VALUES (2, 'u2', 20);
INSERT INTO `user` VALUES (3, 'u3', 30);
INSERT INTO `user` VALUES (4, 'u4', 40);
INSERT INTO `user` VALUES (5, 'u5', 50);
INSERT INTO `user` VALUES (6, 'u6', 60);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
