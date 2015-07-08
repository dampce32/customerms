update t_right set rightCode='SaleByCustomer', rightName='会员卡消费管理', rightUrl ='sale/sales.html',array= '5',isLeaf='1', rightKind='1' where rightId=11;

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
  KEY `FK_Reference_17` (`countCardTypeId`) USING BTREE,
  CONSTRAINT `t_countcard_ibfk_1` FOREIGN KEY (`countCardTypeId`) REFERENCES `t_datadict` (`dataDictId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

SET FOREIGN_KEY_CHECKS=0;

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
  KEY `FK_Reference_50` (`customerId`) USING BTREE,
  KEY `FK_Reference_51` (`countcardId`) USING BTREE,
  CONSTRAINT `t_customerbuycountcard_ibfk_1` FOREIGN KEY (`customerId`) REFERENCES `t_customer` (`customerId`),
  CONSTRAINT `t_customerbuycountcard_ibfk_2` FOREIGN KEY (`countcardId`) REFERENCES `t_countcard` (`countCardId`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

SET FOREIGN_KEY_CHECKS=0;

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
  KEY `AK_AU_Code` (`dataDictType`,`dataDictCode`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='dataDictType\r\ncountCardType--计次卡类型\r\n';


SET FOREIGN_KEY_CHECKS=0;

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
  KEY `salebuycountcard_ref_user` (`userId`) USING BTREE,
  KEY `salebuycountcard_ref_customerbuycountcard` (`customerBuyCountCardId`) USING BTREE,
  CONSTRAINT `t_salebuycountcard_ibfk_1` FOREIGN KEY (`customerBuyCountCardId`) REFERENCES `t_customerbuycountcard` (`customerBuyCountCardId`),
  CONSTRAINT `t_salebuycountcard_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `t_user` (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

