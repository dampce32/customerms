/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : customerms

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2016-10-03 00:23:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_countcard`
-- ----------------------------
DROP TABLE IF EXISTS `t_countcard`;
CREATE TABLE `t_countcard` (
  `countCardId` int(11) NOT NULL AUTO_INCREMENT,
  `countCardTypeId` int(11) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `canSaleCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`countCardId`),
  KEY `FK_Reference_17` (`countCardTypeId`),
  CONSTRAINT `FK_Reference_17` FOREIGN KEY (`countCardTypeId`) REFERENCES `t_datadict` (`dataDictId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_countcard
-- ----------------------------

-- ----------------------------
-- Table structure for `t_customer`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `customerId` int(11) NOT NULL AUTO_INCREMENT,
  `customerTypeId` int(11) DEFAULT NULL,
  `customerCode` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `customerName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `telephone` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `wechat` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `amount` float DEFAULT NULL,
  PRIMARY KEY (`customerId`),
  KEY `AK_A_customerCode` (`customerCode`),
  KEY `FK_Reference_6` (`customerTypeId`),
  CONSTRAINT `FK_Reference_6` FOREIGN KEY (`customerTypeId`) REFERENCES `t_customertype` (`customerTypeId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_customer
-- ----------------------------

-- ----------------------------
-- Table structure for `t_customerbuycountcard`
-- ----------------------------
DROP TABLE IF EXISTS `t_customerbuycountcard`;
CREATE TABLE `t_customerbuycountcard` (
  `customerBuyCountCardId` int(11) NOT NULL AUTO_INCREMENT,
  `customerId` int(11) DEFAULT NULL,
  `countcardId` int(11) DEFAULT NULL,
  `alreadySaleCount` int(11) DEFAULT NULL,
  `buyCountCardDate` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`customerBuyCountCardId`),
  KEY `FK_Reference_50` (`customerId`),
  KEY `FK_Reference_51` (`countcardId`),
  CONSTRAINT `FK_Reference_50` FOREIGN KEY (`customerId`) REFERENCES `t_customer` (`customerId`),
  CONSTRAINT `FK_Reference_51` FOREIGN KEY (`countcardId`) REFERENCES `t_countcard` (`countCardId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_customerbuycountcard
-- ----------------------------

-- ----------------------------
-- Table structure for `t_customerrecharge`
-- ----------------------------
DROP TABLE IF EXISTS `t_customerrecharge`;
CREATE TABLE `t_customerrecharge` (
  `customerRechargeId` int(11) NOT NULL AUTO_INCREMENT,
  `customerId` int(11) DEFAULT NULL,
  `rechargeDate` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `amount` float NOT NULL,
  PRIMARY KEY (`customerRechargeId`),
  KEY `FK_Reference_15` (`customerId`),
  CONSTRAINT `FK_Reference_15` FOREIGN KEY (`customerId`) REFERENCES `t_customer` (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_customerrecharge
-- ----------------------------

-- ----------------------------
-- Table structure for `t_customertype`
-- ----------------------------
DROP TABLE IF EXISTS `t_customertype`;
CREATE TABLE `t_customertype` (
  `customerTypeId` int(11) NOT NULL AUTO_INCREMENT,
  `customerTypeName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `discount` float NOT NULL,
  PRIMARY KEY (`customerTypeId`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_customertype
-- ----------------------------
INSERT INTO `t_customertype` VALUES ('1', '会员', '8');
INSERT INTO `t_customertype` VALUES ('2', '普通会员', '8.8');
INSERT INTO `t_customertype` VALUES ('3', '贵宾', '7');
INSERT INTO `t_customertype` VALUES ('4', '钻石', '6');

-- ----------------------------
-- Table structure for `t_datadict`
-- ----------------------------
DROP TABLE IF EXISTS `t_datadict`;
CREATE TABLE `t_datadict` (
  `dataDictId` int(11) NOT NULL AUTO_INCREMENT,
  `dataDictType` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `dataDictCode` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `dataDictName` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `array` int(11) DEFAULT NULL,
  PRIMARY KEY (`dataDictId`),
  KEY `AK_AU_Code` (`dataDictType`,`dataDictCode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='dataDictType\r\ncountCardType--计次卡类型\r\n';

-- ----------------------------
-- Records of t_datadict
-- ----------------------------

-- ----------------------------
-- Table structure for `t_goods`
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `goodsId` int(11) NOT NULL AUTO_INCREMENT,
  `goodsName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `amount` float NOT NULL,
  `isDiscount` int(11) NOT NULL,
  PRIMARY KEY (`goodsId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_goods
-- ----------------------------

-- ----------------------------
-- Table structure for `t_right`
-- ----------------------------
DROP TABLE IF EXISTS `t_right`;
CREATE TABLE `t_right` (
  `rightId` int(11) NOT NULL AUTO_INCREMENT,
  `parentRightId` int(11) DEFAULT NULL,
  `rightPKCode` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `rightCode` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `rightName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `rightUrl` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `array` int(11) NOT NULL,
  `isLeaf` int(11) NOT NULL,
  `rightKind` int(11) NOT NULL,
  `iconCls` varchar(50) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`rightId`),
  KEY `AK_U_RightCode` (`rightCode`),
  KEY `AK_U_RightPKCode` (`rightPKCode`),
  KEY `FK_Reference_5` (`parentRightId`),
  CONSTRAINT `FK_Reference_5` FOREIGN KEY (`parentRightId`) REFERENCES `t_right` (`rightId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_right
-- ----------------------------
INSERT INTO `t_right` VALUES ('1', null, '', '', '用户权限', null, '1', '0', '1', null, '1');
INSERT INTO `t_right` VALUES ('2', '1', '001', 'System', '系统管理', null, '1', '0', '1', null, '1');
INSERT INTO `t_right` VALUES ('3', '2', '001001', 'System_Right', '权限管理', 'system/rights.html', '1', '1', '1', null, '1');
INSERT INTO `t_right` VALUES ('4', '2', '001002', 'System_Role', '角色管理', 'system/roles.html', '2', '1', '1', null, '1');
INSERT INTO `t_right` VALUES ('5', '2', '001003', 'System_User', '用户管理', 'system/users.html', '3', '1', '1', null, '1');
INSERT INTO `t_right` VALUES ('6', '1', '011', 'dict', '基础字典', null, '2', '0', '1', null, '1');
INSERT INTO `t_right` VALUES ('7', '6', '011001', 'dict_CustomerType', '会员类型', 'dict/customerTypes.html', '1', '1', '1', null, '1');
INSERT INTO `t_right` VALUES ('8', '6', '011011', 'dict_SaleItem', '消费项目', 'dict/saleItems.html', '2', '1', '1', null, '1');
INSERT INTO `t_right` VALUES ('9', '6', '011021', 'dict_Goods', '产品', 'dict/goodss.html', '3', '1', '1', null, '1');
INSERT INTO `t_right` VALUES ('10', '1', '021', 'Customer', '会员管理', 'customer/customers.html', '4', '1', '1', null, '1');
INSERT INTO `t_right` VALUES ('11', '1', '031', 'SaleByCustomer', '会员卡消费管理', 'sale/sales.html', '5', '1', '1', null, '1');
INSERT INTO `t_right` VALUES ('12', '6', '011031', 'dict_CountCardType', '计次卡类型', 'dict/countCardTypes.html', '4', '1', '1', null, '1');
INSERT INTO `t_right` VALUES ('13', '1', '042', 'CountCard', '计次卡管理', 'countCard/countCards.html', '3', '1', '1', null, '1');
INSERT INTO `t_right` VALUES ('14', '1', '052', 'SaleByCountCard', '计次卡消费管理', 'sale/saleBuyCountCard.html', '6', '1', '1', null, '1');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleCode` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `roleName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `array` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`roleId`),
  KEY `AK_U_RoleCode` (`roleCode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', 'admin', '超级管理员', '1', '1');

-- ----------------------------
-- Table structure for `t_roleright`
-- ----------------------------
DROP TABLE IF EXISTS `t_roleright`;
CREATE TABLE `t_roleright` (
  `roleRightId` int(11) NOT NULL AUTO_INCREMENT,
  `rightId` int(11) DEFAULT NULL,
  `roleId` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`roleRightId`),
  KEY `AK_U_RoleRight` (`rightId`,`roleId`),
  KEY `FK_Reference_2` (`roleId`),
  CONSTRAINT `FK_Reference_1` FOREIGN KEY (`rightId`) REFERENCES `t_right` (`rightId`),
  CONSTRAINT `FK_Reference_2` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_roleright
-- ----------------------------
INSERT INTO `t_roleright` VALUES ('1', '1', '1', '1');
INSERT INTO `t_roleright` VALUES ('2', '2', '1', '1');
INSERT INTO `t_roleright` VALUES ('3', '3', '1', '1');
INSERT INTO `t_roleright` VALUES ('4', '4', '1', '1');
INSERT INTO `t_roleright` VALUES ('5', '5', '1', '1');
INSERT INTO `t_roleright` VALUES ('8', '6', '1', '1');
INSERT INTO `t_roleright` VALUES ('9', '7', '1', '1');
INSERT INTO `t_roleright` VALUES ('10', '8', '1', '1');
INSERT INTO `t_roleright` VALUES ('11', '9', '1', '1');
INSERT INTO `t_roleright` VALUES ('12', '10', '1', '1');
INSERT INTO `t_roleright` VALUES ('13', '11', '1', '1');
INSERT INTO `t_roleright` VALUES ('25', '12', '1', '1');
INSERT INTO `t_roleright` VALUES ('28', '13', '1', '1');
INSERT INTO `t_roleright` VALUES ('30', '14', '1', '1');

-- ----------------------------
-- Table structure for `t_sale`
-- ----------------------------
DROP TABLE IF EXISTS `t_sale`;
CREATE TABLE `t_sale` (
  `saleId` int(11) NOT NULL AUTO_INCREMENT,
  `customerId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `saleDate` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `notIntoDiscountAmount` float DEFAULT NULL,
  `intoDiscountAmount` float DEFAULT NULL,
  `discount` float DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `balance` float DEFAULT NULL,
  `payByCard` float DEFAULT NULL,
  `payByCash` float DEFAULT NULL,
  PRIMARY KEY (`saleId`),
  KEY `FK_Reference_14` (`userId`),
  KEY `FK_Reference_7` (`customerId`),
  CONSTRAINT `FK_Reference_14` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`),
  CONSTRAINT `FK_Reference_7` FOREIGN KEY (`customerId`) REFERENCES `t_customer` (`customerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sale
-- ----------------------------

-- ----------------------------
-- Table structure for `t_salebuycountcard`
-- ----------------------------
DROP TABLE IF EXISTS `t_salebuycountcard`;
CREATE TABLE `t_salebuycountcard` (
  `saleBuyCountCardId` int(11) NOT NULL AUTO_INCREMENT,
  `customerBuyCountCardId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `saleDate` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `saleCount` int(11) DEFAULT NULL,
  `canCount` int(11) DEFAULT NULL,
  PRIMARY KEY (`saleBuyCountCardId`),
  KEY `salebuycountcard_ref_user` (`userId`),
  KEY `salebuycountcard_ref_customerbuycountcard` (`customerBuyCountCardId`),
  CONSTRAINT `salebuycountcard_ref_customerbuycountcard` FOREIGN KEY (`customerBuyCountCardId`) REFERENCES `t_customerbuycountcard` (`customerBuyCountCardId`),
  CONSTRAINT `salebuycountcard_ref_user` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_salebuycountcard
-- ----------------------------

-- ----------------------------
-- Table structure for `t_salegoodsdetail`
-- ----------------------------
DROP TABLE IF EXISTS `t_salegoodsdetail`;
CREATE TABLE `t_salegoodsdetail` (
  `saleGoodsDetailId` int(11) NOT NULL AUTO_INCREMENT,
  `saleId` int(11) DEFAULT NULL,
  `goodsId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `amount` float DEFAULT NULL,
  `isDiscount` int(11) DEFAULT NULL,
  PRIMARY KEY (`saleGoodsDetailId`),
  KEY `FK_Reference_11` (`saleId`),
  KEY `FK_Reference_12` (`goodsId`),
  KEY `FK_Reference_13` (`userId`),
  CONSTRAINT `FK_Reference_11` FOREIGN KEY (`saleId`) REFERENCES `t_sale` (`saleId`),
  CONSTRAINT `FK_Reference_12` FOREIGN KEY (`goodsId`) REFERENCES `t_goods` (`goodsId`),
  CONSTRAINT `FK_Reference_13` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_salegoodsdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `t_saleitem`
-- ----------------------------
DROP TABLE IF EXISTS `t_saleitem`;
CREATE TABLE `t_saleitem` (
  `saleItemId` int(11) NOT NULL AUTO_INCREMENT,
  `saleItemName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `amount` float NOT NULL,
  `isDiscount` int(11) NOT NULL,
  PRIMARY KEY (`saleItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_saleitem
-- ----------------------------

-- ----------------------------
-- Table structure for `t_saleitemdetail`
-- ----------------------------
DROP TABLE IF EXISTS `t_saleitemdetail`;
CREATE TABLE `t_saleitemdetail` (
  `saleItemDetailId` int(11) NOT NULL AUTO_INCREMENT,
  `saleId` int(11) NOT NULL,
  `saleItemId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `amount` float NOT NULL,
  `isDiscount` int(11) NOT NULL,
  PRIMARY KEY (`saleItemDetailId`),
  KEY `FK_Reference_10` (`userId`),
  KEY `FK_Reference_8` (`saleId`),
  KEY `FK_Reference_9` (`saleItemId`),
  CONSTRAINT `FK_Reference_10` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`saleId`) REFERENCES `t_sale` (`saleId`),
  CONSTRAINT `FK_Reference_9` FOREIGN KEY (`saleItemId`) REFERENCES `t_saleitem` (`saleItemId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_saleitemdetail
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `userId` int(11) NOT NULL AUTO_INCREMENT,
  `userCode` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `userName` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `passwords` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `status` int(11) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  KEY `AK_U_UserCode` (`userCode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'admin', '超级管理员', 'e10adc3949ba59abbe56e057f20f883e', '1');

-- ----------------------------
-- Table structure for `t_userrole`
-- ----------------------------
DROP TABLE IF EXISTS `t_userrole`;
CREATE TABLE `t_userrole` (
  `userRoleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  PRIMARY KEY (`userRoleId`),
  KEY `AK_U_UserRole` (`roleId`,`userId`),
  KEY `FK_Reference_4` (`userId`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`roleId`) REFERENCES `t_role` (`roleId`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_userrole
-- ----------------------------
INSERT INTO `t_userrole` VALUES ('1', '1', '1');
