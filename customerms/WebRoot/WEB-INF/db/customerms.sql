/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50520
Source Host           : localhost:3306
Source Database       : customerms

Target Server Type    : MYSQL
Target Server Version : 50520
File Encoding         : 65001

Date: 2015-07-08 20:50:41
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_countcard
-- ----------------------------
INSERT INTO `t_countcard` VALUES ('1', '3', '500', '5');
INSERT INTO `t_countcard` VALUES ('2', '3', '100', '1');
INSERT INTO `t_countcard` VALUES ('3', '3', '200', '2');
INSERT INTO `t_countcard` VALUES ('4', '3', '300', '3');
INSERT INTO `t_countcard` VALUES ('5', '4', '500', '5');
INSERT INTO `t_countcard` VALUES ('6', '4', '400', '4');
INSERT INTO `t_countcard` VALUES ('7', '3', '600', '6');
INSERT INTO `t_countcard` VALUES ('8', '3', '700', '7');
INSERT INTO `t_countcard` VALUES ('9', '3', '800', '8');
INSERT INTO `t_countcard` VALUES ('10', '3', '900', '9');
INSERT INTO `t_countcard` VALUES ('11', '3', '1000', '10');
INSERT INTO `t_countcard` VALUES ('12', '3', '200', '3');

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
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('1', '1', 'HY001', '代丽', null, null, '267');
INSERT INTO `t_customer` VALUES ('2', '4', '002', '宋丽芳', null, null, '183');
INSERT INTO `t_customer` VALUES ('3', '1', '003', '翁惠芳', null, null, '436');
INSERT INTO `t_customer` VALUES ('4', '1', '004', '付文蓉', null, null, '104');
INSERT INTO `t_customer` VALUES ('5', '3', '005', '白云', null, null, '398');
INSERT INTO `t_customer` VALUES ('6', '4', '006', '余琴', null, null, '188');
INSERT INTO `t_customer` VALUES ('7', '2', '007', '董小姐', null, null, '300');
INSERT INTO `t_customer` VALUES ('8', '4', '008', '于珺珺', null, null, '226');
INSERT INTO `t_customer` VALUES ('9', '1', '009', '王琳', null, null, '76');
INSERT INTO `t_customer` VALUES ('10', '1', '010', '豆豆', null, null, '154');
INSERT INTO `t_customer` VALUES ('11', '3', '011', '赖雅萍', null, null, '217');
INSERT INTO `t_customer` VALUES ('12', '3', '012', '小芬', null, null, '167');
INSERT INTO `t_customer` VALUES ('13', '4', '013', '高玲', null, null, '157');
INSERT INTO `t_customer` VALUES ('14', '1', '014', '陈婷', null, null, '21');
INSERT INTO `t_customer` VALUES ('15', '3', '0015', '何玫', null, null, '574');
INSERT INTO `t_customer` VALUES ('16', '3', '0016', '娟子', null, null, '410');
INSERT INTO `t_customer` VALUES ('17', '1', '017', '郑捷', null, null, '50');
INSERT INTO `t_customer` VALUES ('18', '1', '0018', '许彤', null, null, '315');
INSERT INTO `t_customer` VALUES ('19', '2', '0019', '沈小丹', null, null, '230');
INSERT INTO `t_customer` VALUES ('20', '1', '0020', '王熠敏', null, null, '199');
INSERT INTO `t_customer` VALUES ('21', '4', '0021', '汪红林', null, null, '655');
INSERT INTO `t_customer` VALUES ('22', '2', '0022', '余月', null, null, '0');
INSERT INTO `t_customer` VALUES ('23', '3', '0023', '何秀娟', null, null, '215');
INSERT INTO `t_customer` VALUES ('24', '3', '0024', '铱铱', '13922297202', null, '422');
INSERT INTO `t_customer` VALUES ('25', '2', '0025', '余', null, null, '243');
INSERT INTO `t_customer` VALUES ('26', '1', '0026', '姚颖', null, null, '270');
INSERT INTO `t_customer` VALUES ('27', '1', '0027', '林赛梅', '13805095899', null, '92');
INSERT INTO `t_customer` VALUES ('28', '2', '0028', '陈默', null, null, '0');
INSERT INTO `t_customer` VALUES ('29', '3', '0029', '吴洁', null, null, '1253');
INSERT INTO `t_customer` VALUES ('30', '1', '0030', '杨美华', null, null, '414');
INSERT INTO `t_customer` VALUES ('31', '2', '0031', '蔡雨兮', null, null, '79');
INSERT INTO `t_customer` VALUES ('32', '3', '0032', '菲菲', null, null, '24');
INSERT INTO `t_customer` VALUES ('33', '1', '0033', '新萍', null, null, '317');
INSERT INTO `t_customer` VALUES ('34', '1', '0034', '陈丽芬', null, null, '273');
INSERT INTO `t_customer` VALUES ('35', '2', '0035', 'KK', null, null, '19');
INSERT INTO `t_customer` VALUES ('36', '3', '0036', '王菲', null, null, '87');
INSERT INTO `t_customer` VALUES ('37', '1', '0037', '韩雪', null, null, '4');
INSERT INTO `t_customer` VALUES ('38', '1', '0038', '林倩楠', null, null, '115');
INSERT INTO `t_customer` VALUES ('39', '3', '0039', '彬彬', null, null, '465');
INSERT INTO `t_customer` VALUES ('40', '1', '0040', '余小芳', null, null, '325');
INSERT INTO `t_customer` VALUES ('41', '1', '0041', '刘鹏', null, null, '71');
INSERT INTO `t_customer` VALUES ('43', '3', '0042', '李琴', null, null, '834');
INSERT INTO `t_customer` VALUES ('44', '1', '0043', '陈丽萍', null, null, '248');
INSERT INTO `t_customer` VALUES ('45', '4', '0047', '林梅', null, null, '140');
INSERT INTO `t_customer` VALUES ('46', '4', '0048', '叶小倩', null, null, '23');
INSERT INTO `t_customer` VALUES ('47', '4', '0049', '黄璐', null, null, '187');
INSERT INTO `t_customer` VALUES ('48', '1', '0045', '张丽芸', null, null, '528');
INSERT INTO `t_customer` VALUES ('49', '1', '0046', '樊凡', null, null, '24');
INSERT INTO `t_customer` VALUES ('50', '4', '0050', '王文清', null, null, '243');
INSERT INTO `t_customer` VALUES ('51', '2', '0051', '施新建', null, null, '169');
INSERT INTO `t_customer` VALUES ('52', '1', '0052', '雅希', '13788879311', null, '167');
INSERT INTO `t_customer` VALUES ('53', '2', '0053', '姚婷', null, null, '161');
INSERT INTO `t_customer` VALUES ('54', '2', '0054', '汤雯', null, null, '78');
INSERT INTO `t_customer` VALUES ('55', '1', '0055', '陈红', null, null, '53');
INSERT INTO `t_customer` VALUES ('56', '4', '0056', '林莹', null, null, '15');
INSERT INTO `t_customer` VALUES ('57', '2', '0057', '乐乐', null, null, '145');
INSERT INTO `t_customer` VALUES ('58', '1', '0058', '田玉', null, null, '346');
INSERT INTO `t_customer` VALUES ('59', '1', '0059', '陈美兰', null, null, '72');
INSERT INTO `t_customer` VALUES ('60', '1', '0060', '王金丹', null, null, '272');
INSERT INTO `t_customer` VALUES ('61', '3', '0061', '吴斌', null, null, '976');
INSERT INTO `t_customer` VALUES ('62', '4', '0062', '敏敏', null, null, '251');
INSERT INTO `t_customer` VALUES ('63', '1', '0063', '周美乐', null, null, '3');
INSERT INTO `t_customer` VALUES ('64', '4', '0065', '郭卫红', null, null, '1736');
INSERT INTO `t_customer` VALUES ('65', '1', '0066', '曾春妹', null, null, '198');
INSERT INTO `t_customer` VALUES ('66', '3', '0067', '齐燕凌', null, null, '1148');
INSERT INTO `t_customer` VALUES ('67', '3', '0068', '陈怡彤', null, null, '1296');
INSERT INTO `t_customer` VALUES ('68', '4', '0069', '陈丽英', null, null, '217');
INSERT INTO `t_customer` VALUES ('69', '1', '0070', '李瑜', null, null, '193');
INSERT INTO `t_customer` VALUES ('70', '1', '0071', '商锦芳', null, null, '84');
INSERT INTO `t_customer` VALUES ('71', '3', '0072', '陈凤', null, null, '110');
INSERT INTO `t_customer` VALUES ('72', '1', '0078', '林小贝', null, null, '63');
INSERT INTO `t_customer` VALUES ('73', '4', '0079', '林峥嵘', null, null, '86');
INSERT INTO `t_customer` VALUES ('74', '3', '0099', '李天祺', null, null, '1412');
INSERT INTO `t_customer` VALUES ('75', '2', '0100', '林霞', null, null, '20');
INSERT INTO `t_customer` VALUES ('76', '1', '0101', '严丽萍', null, null, '158');
INSERT INTO `t_customer` VALUES ('77', '2', '0102', '王艳', null, null, '97');
INSERT INTO `t_customer` VALUES ('78', '3', '0103', '郑晶晶', null, null, '197');
INSERT INTO `t_customer` VALUES ('79', '3', '0104', '金琰', null, null, '185');
INSERT INTO `t_customer` VALUES ('80', '1', '0105', '张小云', null, null, '373');
INSERT INTO `t_customer` VALUES ('81', '3', '0106', '林木华', null, null, '275');
INSERT INTO `t_customer` VALUES ('82', '2', '0107', '李芳1', null, null, '30');
INSERT INTO `t_customer` VALUES ('83', '1', '0108', '程博', null, null, '1');
INSERT INTO `t_customer` VALUES ('84', '1', '0109', '小爱', null, null, '191');
INSERT INTO `t_customer` VALUES ('85', '1', '0110', '杨雅婷', null, null, '180');
INSERT INTO `t_customer` VALUES ('86', '1', '0111', '戴梦菲', null, null, '161');
INSERT INTO `t_customer` VALUES ('87', '2', '0112', '谢婷英', null, null, '112');
INSERT INTO `t_customer` VALUES ('88', '2', '0113', '黄彩云', null, null, '107');
INSERT INTO `t_customer` VALUES ('89', '2', '0114', '刘佳', null, null, '135');
INSERT INTO `t_customer` VALUES ('90', '2', '0115', '许映娟', null, null, '0');
INSERT INTO `t_customer` VALUES ('91', '4', '0116', '朱红梅', null, null, '1146');
INSERT INTO `t_customer` VALUES ('92', '1', '0117', '陈枫', null, null, '339');
INSERT INTO `t_customer` VALUES ('93', '1', '0118', '罗倩', null, null, '446');
INSERT INTO `t_customer` VALUES ('94', '2', '0119', '小喻', null, null, '126');
INSERT INTO `t_customer` VALUES ('95', '1', '0120', '双双', null, null, '192');
INSERT INTO `t_customer` VALUES ('96', '4', '0121', '施惠云', null, null, '476');
INSERT INTO `t_customer` VALUES ('97', '4', '0122', '陈玉钦', null, null, '232');
INSERT INTO `t_customer` VALUES ('98', '1', '0123', '魏雪儿', null, null, '72');
INSERT INTO `t_customer` VALUES ('99', '1', '0124', '林梦艺', null, null, '161');
INSERT INTO `t_customer` VALUES ('100', '1', '0125', '李红', null, null, '84');
INSERT INTO `t_customer` VALUES ('101', '4', '0126', '燕平', null, null, '95');
INSERT INTO `t_customer` VALUES ('102', '4', '0127', '张娜', null, null, '1076');
INSERT INTO `t_customer` VALUES ('103', '1', '0128', '张霖飞', null, null, '64');
INSERT INTO `t_customer` VALUES ('104', '1', '0129', '韩小菲', null, null, '46');
INSERT INTO `t_customer` VALUES ('105', '1', '0130', '赵飞燕', null, null, '272');
INSERT INTO `t_customer` VALUES ('106', '2', '0131', '方小姐', null, null, '30');
INSERT INTO `t_customer` VALUES ('107', '3', '0132', '李羽希', null, null, '170');
INSERT INTO `t_customer` VALUES ('108', '3', '0133', '许冬', null, null, '72');
INSERT INTO `t_customer` VALUES ('109', '2', '0134', '赵静', null, null, '58');
INSERT INTO `t_customer` VALUES ('110', '1', '0135', '余闽瑶', null, null, '48');
INSERT INTO `t_customer` VALUES ('111', '2', '0136', '黄倩莉', null, null, '125');
INSERT INTO `t_customer` VALUES ('112', '2', '0137', '任龙珠', null, null, '4');
INSERT INTO `t_customer` VALUES ('113', '1', '0138', '周蔓', null, null, '261');
INSERT INTO `t_customer` VALUES ('114', '1', '0139', '叶静', null, null, '16');
INSERT INTO `t_customer` VALUES ('115', '1', '0140', '媛媛', null, null, '37');
INSERT INTO `t_customer` VALUES ('116', '4', '0141', '王路', null, null, '271');
INSERT INTO `t_customer` VALUES ('117', '4', '0142', '吴琪琪', null, null, '17');
INSERT INTO `t_customer` VALUES ('118', '1', '0143', '李丽', null, null, '76');
INSERT INTO `t_customer` VALUES ('119', '1', '0144', '林祥蓉', null, null, '204');
INSERT INTO `t_customer` VALUES ('120', '1', '0145', '孙艳', null, null, '473');
INSERT INTO `t_customer` VALUES ('121', '1', '0146', '可可', null, null, '0');
INSERT INTO `t_customer` VALUES ('122', '1', '0147', '茜', null, null, '140');
INSERT INTO `t_customer` VALUES ('123', '1', '0148', '刘燕', null, null, '429');
INSERT INTO `t_customer` VALUES ('124', '1', '0149', '夏节', null, null, '309');
INSERT INTO `t_customer` VALUES ('125', '1', '0150', '刘小玲', null, null, '87');
INSERT INTO `t_customer` VALUES ('126', '3', '0151', '张明榕', null, null, '275');
INSERT INTO `t_customer` VALUES ('127', '1', '0152', '林晓莉', null, null, '267');
INSERT INTO `t_customer` VALUES ('128', '2', '0153', '林维', null, null, '124');
INSERT INTO `t_customer` VALUES ('129', '3', '0154', '叶泓', null, null, '1023');
INSERT INTO `t_customer` VALUES ('130', '1', '0155', '陈跃明', null, null, '110');
INSERT INTO `t_customer` VALUES ('131', '2', '0156', '赵文', null, null, '1');
INSERT INTO `t_customer` VALUES ('132', '4', '0157', '吴佩怡', null, null, '40');
INSERT INTO `t_customer` VALUES ('133', '2', '0158', '雅雅', null, null, '29');
INSERT INTO `t_customer` VALUES ('134', '2', '0159', '刘文清', null, null, '35');
INSERT INTO `t_customer` VALUES ('135', '1', '0160', '游云平', null, null, '216');
INSERT INTO `t_customer` VALUES ('136', '2', '0161', '苏萍萍', null, null, '0');
INSERT INTO `t_customer` VALUES ('137', '2', '0162', '郑微', null, null, '55');
INSERT INTO `t_customer` VALUES ('138', '2', '0163', '蔡雨含', null, null, '87');
INSERT INTO `t_customer` VALUES ('139', '1', '0164', '林萍', null, null, '302');
INSERT INTO `t_customer` VALUES ('140', '3', '0165', '张丽云', null, null, '284');
INSERT INTO `t_customer` VALUES ('141', '1', '800802', 'LD', '13799958887', null, '296');
INSERT INTO `t_customer` VALUES ('142', '1', '00166', '张姐', null, null, '0');
INSERT INTO `t_customer` VALUES ('143', '2', '00167', '余春梅', null, null, '0');
INSERT INTO `t_customer` VALUES ('144', '1', '0168', '香琴', null, null, '0');
INSERT INTO `t_customer` VALUES ('145', '2', '0169', '许晶晶', null, null, '0');
INSERT INTO `t_customer` VALUES ('146', '1', '0170', 'JOJO', null, null, '0');
INSERT INTO `t_customer` VALUES ('147', '2', '0171', '包秀研', null, null, '0');
INSERT INTO `t_customer` VALUES ('148', '1', '0172', '江平', null, null, '52');
INSERT INTO `t_customer` VALUES ('149', '1', '0175', '李姝', null, null, '0');
INSERT INTO `t_customer` VALUES ('150', '1', '0176', '宋明媛', null, null, '488');
INSERT INTO `t_customer` VALUES ('151', '2', '0177', '陈冰林', null, null, '56');
INSERT INTO `t_customer` VALUES ('152', '2', '800639', '郑敏', null, null, '98');
INSERT INTO `t_customer` VALUES ('153', '3', '0179', '张明榕', null, null, '296');
INSERT INTO `t_customer` VALUES ('154', '2', '0180', '林维', null, null, '212');
INSERT INTO `t_customer` VALUES ('155', '2', '0181', '吴灵英', null, null, '164');
INSERT INTO `t_customer` VALUES ('156', '2', '800803', '李红梅1', '13625089838', null, '221');
INSERT INTO `t_customer` VALUES ('157', '1', '0183', '何小凡', null, null, '0');
INSERT INTO `t_customer` VALUES ('158', '2', '0185', '殷切', null, null, '0');
INSERT INTO `t_customer` VALUES ('159', '1', '0186', '李茜', null, null, '12');
INSERT INTO `t_customer` VALUES ('160', '2', '0187', '许心玫', null, null, '28');
INSERT INTO `t_customer` VALUES ('161', '1', '0188', '沈春香', null, null, '207');
INSERT INTO `t_customer` VALUES ('162', '3', '0189', '杨采真', null, null, '146');
INSERT INTO `t_customer` VALUES ('163', '1', '0190', '李娜', null, null, '138');
INSERT INTO `t_customer` VALUES ('164', '1', '0191', '曾丽云', null, null, '45');
INSERT INTO `t_customer` VALUES ('165', '1', '800801', '林琳', '15960970777', null, '408');
INSERT INTO `t_customer` VALUES ('166', '2', '0200', '林可可', null, null, '74');
INSERT INTO `t_customer` VALUES ('167', '1', '0201', '许玉翠', null, null, '46');
INSERT INTO `t_customer` VALUES ('168', '3', '0202', '林紫', null, null, '1017');
INSERT INTO `t_customer` VALUES ('169', '3', '0203', '唐小', null, null, '4');
INSERT INTO `t_customer` VALUES ('170', '2', '0205', '林雅', null, null, '124');
INSERT INTO `t_customer` VALUES ('171', '1', '0206', '陈良亚', null, null, '74');
INSERT INTO `t_customer` VALUES ('172', '1', '0207', '陈静', null, null, '89');
INSERT INTO `t_customer` VALUES ('173', '3', '0208', '郑晓梅', null, null, '1132');
INSERT INTO `t_customer` VALUES ('174', '2', '0209', '王燕', null, null, '175');
INSERT INTO `t_customer` VALUES ('175', '2', '0210', '戴玉华', null, null, '218');
INSERT INTO `t_customer` VALUES ('176', '2', '0211', '汪情', null, null, '0');
INSERT INTO `t_customer` VALUES ('177', '1', '0212', '高艳芳', null, null, '344');
INSERT INTO `t_customer` VALUES ('178', '2', '0213', '陈莹', null, null, '0');
INSERT INTO `t_customer` VALUES ('179', '4', '0214', '王爱波', null, null, '273');
INSERT INTO `t_customer` VALUES ('180', '1', '0215', '王芊芊', null, null, '0');
INSERT INTO `t_customer` VALUES ('181', '2', '0216', '小九', null, null, '0');
INSERT INTO `t_customer` VALUES ('182', '2', '0217', '薛雅芳', null, null, '21');
INSERT INTO `t_customer` VALUES ('183', '1', '0218', '蔡琳', null, null, '78');
INSERT INTO `t_customer` VALUES ('184', '1', '0219', '王妙菊', null, null, '163');
INSERT INTO `t_customer` VALUES ('185', '1', '0220', '张星语', null, null, '0');
INSERT INTO `t_customer` VALUES ('186', '1', '0221', '陈美芳', null, null, '313');
INSERT INTO `t_customer` VALUES ('187', '1', '0222', '陈美珍', null, null, '568');
INSERT INTO `t_customer` VALUES ('188', '2', '0223', '李爱梅', null, null, '0');
INSERT INTO `t_customer` VALUES ('189', '1', '0225', '严惠', null, null, '19');
INSERT INTO `t_customer` VALUES ('190', '2', '0226', '魏小也', null, null, '17');
INSERT INTO `t_customer` VALUES ('191', '2', '0227', '姚惠敏', null, null, '86');
INSERT INTO `t_customer` VALUES ('192', '2', '0228', '林秋岚', null, null, '23');
INSERT INTO `t_customer` VALUES ('193', '1', '0229', '杨洁', null, null, '8');
INSERT INTO `t_customer` VALUES ('194', '2', '0230', '竺', null, null, '47');
INSERT INTO `t_customer` VALUES ('195', '3', '0231', '周洲', null, null, '84');
INSERT INTO `t_customer` VALUES ('196', '1', '0232', '陈燕', null, null, '384');
INSERT INTO `t_customer` VALUES ('197', '1', '0233', '高小倩', null, null, '6');
INSERT INTO `t_customer` VALUES ('198', '4', '0234', '韩旭', null, null, '33');
INSERT INTO `t_customer` VALUES ('199', '2', '0235', '林依玉', null, null, '256');
INSERT INTO `t_customer` VALUES ('200', '1', '0236', '蔡琳琳', null, null, '29');
INSERT INTO `t_customer` VALUES ('201', '1', '0237', '殷学兰', null, null, '55');
INSERT INTO `t_customer` VALUES ('202', '1', '22', '22', '22', '22', '0');
INSERT INTO `t_customer` VALUES ('203', '2', 'xx', 'xx', null, null, '0');
INSERT INTO `t_customer` VALUES ('204', '2', 'js001', 'lys', null, null, '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=206 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_customerbuycountcard
-- ----------------------------
INSERT INTO `t_customerbuycountcard` VALUES ('201', '1', '1', null, '2015-02-04 00:22:033');
INSERT INTO `t_customerbuycountcard` VALUES ('202', '2', '5', null, '2015-02-04 00:27:053');
INSERT INTO `t_customerbuycountcard` VALUES ('203', '3', '7', '0', '2015-02-04 00:33:032');
INSERT INTO `t_customerbuycountcard` VALUES ('204', '10', '12', '0', '2015-02-05 22:51:009');
INSERT INTO `t_customerbuycountcard` VALUES ('205', '204', '6', '0', '2015-02-05 22:54:044');

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
) ENGINE=InnoDB AUTO_INCREMENT=189 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_customerrecharge
-- ----------------------------
INSERT INTO `t_customerrecharge` VALUES ('1', '1', '2014-09-10 23:18:017', '100');
INSERT INTO `t_customerrecharge` VALUES ('2', '1', '2014-09-11 12:36:010', '167');
INSERT INTO `t_customerrecharge` VALUES ('3', '2', '2014-09-11 12:44:042', '183');
INSERT INTO `t_customerrecharge` VALUES ('4', '3', '2014-09-11 12:46:048', '436');
INSERT INTO `t_customerrecharge` VALUES ('5', '4', '2014-09-11 12:47:038', '104');
INSERT INTO `t_customerrecharge` VALUES ('6', '5', '2014-09-11 12:49:002', '398');
INSERT INTO `t_customerrecharge` VALUES ('7', '6', '2014-09-11 12:50:011', '188');
INSERT INTO `t_customerrecharge` VALUES ('8', '7', '2014-09-11 12:51:009', '300');
INSERT INTO `t_customerrecharge` VALUES ('9', '8', '2014-09-11 12:52:024', '226');
INSERT INTO `t_customerrecharge` VALUES ('10', '9', '2014-09-11 12:53:005', '76');
INSERT INTO `t_customerrecharge` VALUES ('11', '10', '2014-09-11 12:54:014', '154');
INSERT INTO `t_customerrecharge` VALUES ('12', '11', '2014-09-11 12:55:033', '217');
INSERT INTO `t_customerrecharge` VALUES ('13', '12', '2014-09-11 12:56:049', '167');
INSERT INTO `t_customerrecharge` VALUES ('14', '13', '2014-09-11 12:58:003', '157');
INSERT INTO `t_customerrecharge` VALUES ('15', '14', '2014-09-11 12:58:050', '21');
INSERT INTO `t_customerrecharge` VALUES ('16', '15', '2014-09-11 12:59:042', '574');
INSERT INTO `t_customerrecharge` VALUES ('17', '16', '2014-09-11 13:00:029', '410');
INSERT INTO `t_customerrecharge` VALUES ('18', '17', '2014-09-11 13:02:008', '50');
INSERT INTO `t_customerrecharge` VALUES ('19', '18', '2014-09-11 13:02:059', '315');
INSERT INTO `t_customerrecharge` VALUES ('20', '19', '2014-09-11 13:04:007', '230');
INSERT INTO `t_customerrecharge` VALUES ('21', '20', '2014-09-11 13:05:031', '199');
INSERT INTO `t_customerrecharge` VALUES ('22', '21', '2014-09-11 13:06:041', '655');
INSERT INTO `t_customerrecharge` VALUES ('23', '23', '2014-09-11 13:09:010', '139');
INSERT INTO `t_customerrecharge` VALUES ('24', '24', '2014-09-11 13:10:019', '422');
INSERT INTO `t_customerrecharge` VALUES ('25', '25', '2014-09-11 13:11:007', '243');
INSERT INTO `t_customerrecharge` VALUES ('26', '26', '2014-09-11 13:12:011', '270');
INSERT INTO `t_customerrecharge` VALUES ('27', '27', '2014-09-11 13:13:006', '92');
INSERT INTO `t_customerrecharge` VALUES ('28', '29', '2014-09-11 13:15:020', '1253');
INSERT INTO `t_customerrecharge` VALUES ('29', '30', '2014-09-11 13:16:023', '414');
INSERT INTO `t_customerrecharge` VALUES ('30', '31', '2014-09-11 13:17:021', '79');
INSERT INTO `t_customerrecharge` VALUES ('31', '32', '2014-09-11 13:18:020', '24');
INSERT INTO `t_customerrecharge` VALUES ('32', '33', '2014-09-11 13:19:009', '317');
INSERT INTO `t_customerrecharge` VALUES ('33', '34', '2014-09-11 13:19:053', '273');
INSERT INTO `t_customerrecharge` VALUES ('34', '35', '2014-09-11 13:20:033', '19');
INSERT INTO `t_customerrecharge` VALUES ('35', '36', '2014-09-11 13:21:019', '87');
INSERT INTO `t_customerrecharge` VALUES ('36', '37', '2014-09-11 13:22:021', '4');
INSERT INTO `t_customerrecharge` VALUES ('37', '38', '2014-09-11 13:23:026', '115');
INSERT INTO `t_customerrecharge` VALUES ('38', '39', '2014-09-11 13:24:031', '465');
INSERT INTO `t_customerrecharge` VALUES ('39', '40', '2014-09-11 13:25:043', '325');
INSERT INTO `t_customerrecharge` VALUES ('40', '41', '2014-09-11 13:56:041', '71');
INSERT INTO `t_customerrecharge` VALUES ('41', '43', '2014-09-11 14:00:005', '834');
INSERT INTO `t_customerrecharge` VALUES ('42', '44', '2014-09-11 14:02:056', '248');
INSERT INTO `t_customerrecharge` VALUES ('43', '45', '2014-09-11 14:04:009', '240');
INSERT INTO `t_customerrecharge` VALUES ('44', '46', '2014-09-11 14:05:025', '23');
INSERT INTO `t_customerrecharge` VALUES ('45', '47', '2014-09-11 14:08:005', '187');
INSERT INTO `t_customerrecharge` VALUES ('46', '48', '2014-09-11 14:09:055', '528');
INSERT INTO `t_customerrecharge` VALUES ('47', '49', '2014-09-11 15:11:054', '24');
INSERT INTO `t_customerrecharge` VALUES ('48', '50', '2014-09-11 15:13:043', '243');
INSERT INTO `t_customerrecharge` VALUES ('49', '51', '2014-09-11 15:21:049', '169');
INSERT INTO `t_customerrecharge` VALUES ('50', '52', '2014-09-11 15:23:001', '167');
INSERT INTO `t_customerrecharge` VALUES ('51', '53', '2014-09-11 15:24:015', '161');
INSERT INTO `t_customerrecharge` VALUES ('52', '54', '2014-09-11 15:26:059', '78');
INSERT INTO `t_customerrecharge` VALUES ('53', '55', '2014-09-11 15:28:006', '53');
INSERT INTO `t_customerrecharge` VALUES ('54', '56', '2014-09-11 15:29:059', '15');
INSERT INTO `t_customerrecharge` VALUES ('55', '57', '2014-09-11 15:31:024', '145');
INSERT INTO `t_customerrecharge` VALUES ('56', '58', '2014-09-11 15:32:054', '346');
INSERT INTO `t_customerrecharge` VALUES ('57', '59', '2014-09-11 15:33:051', '72');
INSERT INTO `t_customerrecharge` VALUES ('58', '60', '2014-09-11 15:34:057', '272');
INSERT INTO `t_customerrecharge` VALUES ('59', '61', '2014-09-11 15:37:017', '976');
INSERT INTO `t_customerrecharge` VALUES ('60', '62', '2014-09-11 15:38:012', '251');
INSERT INTO `t_customerrecharge` VALUES ('61', '63', '2014-09-11 15:39:003', '3');
INSERT INTO `t_customerrecharge` VALUES ('62', '64', '2014-09-11 15:40:021', '1736');
INSERT INTO `t_customerrecharge` VALUES ('63', '65', '2014-09-11 15:41:041', '198');
INSERT INTO `t_customerrecharge` VALUES ('64', '66', '2014-09-11 15:42:052', '1148');
INSERT INTO `t_customerrecharge` VALUES ('65', '67', '2014-09-11 15:43:055', '1296');
INSERT INTO `t_customerrecharge` VALUES ('66', '68', '2014-09-11 15:48:058', '217');
INSERT INTO `t_customerrecharge` VALUES ('67', '69', '2014-09-11 15:50:019', '201');
INSERT INTO `t_customerrecharge` VALUES ('68', '70', '2014-09-11 15:53:047', '84');
INSERT INTO `t_customerrecharge` VALUES ('69', '71', '2014-09-11 15:54:054', '110');
INSERT INTO `t_customerrecharge` VALUES ('70', '72', '2014-09-11 15:56:016', '63');
INSERT INTO `t_customerrecharge` VALUES ('71', '73', '2014-09-11 15:58:036', '86');
INSERT INTO `t_customerrecharge` VALUES ('72', '74', '2014-09-11 15:59:059', '1412');
INSERT INTO `t_customerrecharge` VALUES ('73', '75', '2014-09-11 16:01:023', '20');
INSERT INTO `t_customerrecharge` VALUES ('74', '76', '2014-09-11 16:03:053', '158');
INSERT INTO `t_customerrecharge` VALUES ('75', '77', '2014-09-11 16:05:028', '97');
INSERT INTO `t_customerrecharge` VALUES ('76', '78', '2014-09-11 16:08:046', '197');
INSERT INTO `t_customerrecharge` VALUES ('77', '79', '2014-09-11 16:09:057', '185');
INSERT INTO `t_customerrecharge` VALUES ('78', '80', '2014-09-11 16:13:008', '373');
INSERT INTO `t_customerrecharge` VALUES ('79', '81', '2014-09-11 16:14:017', '275');
INSERT INTO `t_customerrecharge` VALUES ('80', '82', '2014-09-11 16:15:018', '30');
INSERT INTO `t_customerrecharge` VALUES ('81', '83', '2014-09-11 16:15:053', '1');
INSERT INTO `t_customerrecharge` VALUES ('82', '84', '2014-09-11 16:16:028', '191');
INSERT INTO `t_customerrecharge` VALUES ('83', '85', '2014-09-11 16:17:015', '180');
INSERT INTO `t_customerrecharge` VALUES ('84', '86', '2014-09-11 16:18:017', '133');
INSERT INTO `t_customerrecharge` VALUES ('85', '87', '2014-09-11 16:19:056', '112');
INSERT INTO `t_customerrecharge` VALUES ('86', '88', '2014-09-11 16:20:034', '116');
INSERT INTO `t_customerrecharge` VALUES ('87', '89', '2014-09-11 16:21:004', '135');
INSERT INTO `t_customerrecharge` VALUES ('88', '91', '2014-09-11 16:23:014', '1146');
INSERT INTO `t_customerrecharge` VALUES ('89', '92', '2014-09-11 16:23:057', '339');
INSERT INTO `t_customerrecharge` VALUES ('90', '93', '2014-09-11 16:24:049', '446');
INSERT INTO `t_customerrecharge` VALUES ('91', '94', '2014-09-11 16:26:030', '126');
INSERT INTO `t_customerrecharge` VALUES ('92', '95', '2014-09-11 16:27:004', '192');
INSERT INTO `t_customerrecharge` VALUES ('93', '96', '2014-09-11 16:28:004', '476');
INSERT INTO `t_customerrecharge` VALUES ('94', '97', '2014-09-11 16:29:006', '232');
INSERT INTO `t_customerrecharge` VALUES ('95', '98', '2014-09-11 16:29:051', '72');
INSERT INTO `t_customerrecharge` VALUES ('96', '99', '2014-09-11 16:32:019', '161');
INSERT INTO `t_customerrecharge` VALUES ('97', '100', '2014-09-11 16:33:000', '84');
INSERT INTO `t_customerrecharge` VALUES ('98', '101', '2014-09-11 16:33:041', '95');
INSERT INTO `t_customerrecharge` VALUES ('99', '102', '2014-09-11 16:35:033', '1076');
INSERT INTO `t_customerrecharge` VALUES ('100', '103', '2014-09-11 16:36:049', '64');
INSERT INTO `t_customerrecharge` VALUES ('101', '104', '2014-09-11 16:39:042', '46');
INSERT INTO `t_customerrecharge` VALUES ('102', '105', '2014-09-11 16:41:000', '272');
INSERT INTO `t_customerrecharge` VALUES ('103', '106', '2014-09-11 16:41:039', '30');
INSERT INTO `t_customerrecharge` VALUES ('104', '107', '2014-09-11 16:44:020', '170');
INSERT INTO `t_customerrecharge` VALUES ('105', '108', '2014-09-11 16:45:010', '72');
INSERT INTO `t_customerrecharge` VALUES ('106', '109', '2014-09-11 16:46:006', '58');
INSERT INTO `t_customerrecharge` VALUES ('107', '110', '2014-09-11 16:47:038', '48');
INSERT INTO `t_customerrecharge` VALUES ('108', '111', '2014-09-11 16:49:027', '125');
INSERT INTO `t_customerrecharge` VALUES ('109', '112', '2014-09-11 16:50:009', '4');
INSERT INTO `t_customerrecharge` VALUES ('110', '113', '2014-09-11 16:51:002', '261');
INSERT INTO `t_customerrecharge` VALUES ('111', '114', '2014-09-11 16:54:007', '38');
INSERT INTO `t_customerrecharge` VALUES ('112', '115', '2014-09-11 16:54:055', '37');
INSERT INTO `t_customerrecharge` VALUES ('113', '116', '2014-09-11 16:55:038', '271');
INSERT INTO `t_customerrecharge` VALUES ('114', '117', '2014-09-11 16:56:028', '17');
INSERT INTO `t_customerrecharge` VALUES ('115', '118', '2014-09-11 16:57:009', '76');
INSERT INTO `t_customerrecharge` VALUES ('116', '119', '2014-09-11 16:59:031', '204');
INSERT INTO `t_customerrecharge` VALUES ('117', '120', '2014-09-11 17:01:005', '473');
INSERT INTO `t_customerrecharge` VALUES ('118', '122', '2014-09-11 17:02:052', '140');
INSERT INTO `t_customerrecharge` VALUES ('119', '123', '2014-09-11 17:06:002', '25');
INSERT INTO `t_customerrecharge` VALUES ('120', '124', '2014-09-11 17:08:000', '309');
INSERT INTO `t_customerrecharge` VALUES ('121', '125', '2014-09-11 17:08:042', '87');
INSERT INTO `t_customerrecharge` VALUES ('122', '126', '2014-09-11 17:11:003', '275');
INSERT INTO `t_customerrecharge` VALUES ('123', '127', '2014-09-11 17:11:055', '57');
INSERT INTO `t_customerrecharge` VALUES ('124', '128', '2014-09-11 17:13:004', '124');
INSERT INTO `t_customerrecharge` VALUES ('125', '129', '2014-09-11 17:13:049', '1023');
INSERT INTO `t_customerrecharge` VALUES ('126', '130', '2014-09-11 17:14:051', '110');
INSERT INTO `t_customerrecharge` VALUES ('127', '131', '2014-09-11 17:15:058', '1');
INSERT INTO `t_customerrecharge` VALUES ('128', '132', '2014-09-11 17:16:038', '40');
INSERT INTO `t_customerrecharge` VALUES ('129', '133', '2014-09-11 17:17:035', '29');
INSERT INTO `t_customerrecharge` VALUES ('130', '134', '2014-09-11 17:18:014', '35');
INSERT INTO `t_customerrecharge` VALUES ('131', '135', '2014-09-11 17:19:011', '216');
INSERT INTO `t_customerrecharge` VALUES ('132', '137', '2014-09-11 17:21:041', '55');
INSERT INTO `t_customerrecharge` VALUES ('133', '138', '2014-09-11 17:25:055', '87');
INSERT INTO `t_customerrecharge` VALUES ('134', '139', '2014-09-11 17:27:021', '302');
INSERT INTO `t_customerrecharge` VALUES ('135', '140', '2014-09-11 19:46:011', '284');
INSERT INTO `t_customerrecharge` VALUES ('136', '141', '2014-09-11 19:47:007', '196');
INSERT INTO `t_customerrecharge` VALUES ('137', '141', '2014-09-11 19:47:018', '100');
INSERT INTO `t_customerrecharge` VALUES ('138', '114', '2014-09-11 20:21:030', '2');
INSERT INTO `t_customerrecharge` VALUES ('139', '148', '2014-09-11 20:27:004', '52');
INSERT INTO `t_customerrecharge` VALUES ('140', '127', '2014-09-11 20:30:052', '200');
INSERT INTO `t_customerrecharge` VALUES ('141', '127', '2014-09-11 20:33:025', '10');
INSERT INTO `t_customerrecharge` VALUES ('142', '150', '2014-09-11 20:35:037', '488');
INSERT INTO `t_customerrecharge` VALUES ('143', '151', '2014-09-11 20:36:043', '56');
INSERT INTO `t_customerrecharge` VALUES ('144', '152', '2014-09-11 20:41:029', '98');
INSERT INTO `t_customerrecharge` VALUES ('145', '153', '2014-09-11 20:44:007', '296');
INSERT INTO `t_customerrecharge` VALUES ('146', '154', '2014-09-11 20:46:006', '212');
INSERT INTO `t_customerrecharge` VALUES ('147', '155', '2014-09-11 20:47:053', '164');
INSERT INTO `t_customerrecharge` VALUES ('148', '156', '2014-09-11 20:50:024', '221');
INSERT INTO `t_customerrecharge` VALUES ('149', '159', '2014-09-11 20:56:004', '12');
INSERT INTO `t_customerrecharge` VALUES ('150', '160', '2014-09-11 20:59:049', '28');
INSERT INTO `t_customerrecharge` VALUES ('151', '161', '2014-09-11 21:05:051', '207');
INSERT INTO `t_customerrecharge` VALUES ('152', '162', '2014-09-11 21:07:024', '146');
INSERT INTO `t_customerrecharge` VALUES ('153', '163', '2014-09-11 21:10:038', '295');
INSERT INTO `t_customerrecharge` VALUES ('154', '164', '2014-09-11 21:12:029', '45');
INSERT INTO `t_customerrecharge` VALUES ('155', '165', '2014-09-12 13:23:054', '408');
INSERT INTO `t_customerrecharge` VALUES ('156', '123', '2014-09-12 14:03:039', '500');
INSERT INTO `t_customerrecharge` VALUES ('157', '166', '2014-09-12 14:09:056', '74');
INSERT INTO `t_customerrecharge` VALUES ('158', '167', '2014-09-12 14:11:037', '46');
INSERT INTO `t_customerrecharge` VALUES ('159', '168', '2014-09-12 14:15:007', '1017');
INSERT INTO `t_customerrecharge` VALUES ('160', '169', '2014-09-12 14:22:014', '4');
INSERT INTO `t_customerrecharge` VALUES ('161', '170', '2014-09-12 14:32:017', '124');
INSERT INTO `t_customerrecharge` VALUES ('162', '171', '2014-09-12 14:35:051', '74');
INSERT INTO `t_customerrecharge` VALUES ('163', '172', '2014-09-12 14:37:040', '89');
INSERT INTO `t_customerrecharge` VALUES ('164', '173', '2014-09-12 14:40:053', '1132');
INSERT INTO `t_customerrecharge` VALUES ('165', '86', '2014-09-12 15:57:047', '300');
INSERT INTO `t_customerrecharge` VALUES ('166', '23', '2014-09-12 20:10:022', '300');
INSERT INTO `t_customerrecharge` VALUES ('167', '174', '2014-09-12 20:18:004', '175');
INSERT INTO `t_customerrecharge` VALUES ('168', '175', '2014-09-12 20:18:053', '218');
INSERT INTO `t_customerrecharge` VALUES ('169', '177', '2014-09-12 20:20:023', '344');
INSERT INTO `t_customerrecharge` VALUES ('170', '179', '2014-09-12 20:22:034', '273');
INSERT INTO `t_customerrecharge` VALUES ('171', '182', '2014-09-12 20:34:008', '21');
INSERT INTO `t_customerrecharge` VALUES ('172', '183', '2014-09-12 20:34:059', '78');
INSERT INTO `t_customerrecharge` VALUES ('173', '184', '2014-09-12 20:36:022', '163');
INSERT INTO `t_customerrecharge` VALUES ('174', '186', '2014-09-12 20:38:010', '313');
INSERT INTO `t_customerrecharge` VALUES ('175', '187', '2014-09-12 20:38:056', '568');
INSERT INTO `t_customerrecharge` VALUES ('176', '189', '2014-09-12 20:43:028', '19');
INSERT INTO `t_customerrecharge` VALUES ('177', '190', '2014-09-12 20:44:054', '17');
INSERT INTO `t_customerrecharge` VALUES ('178', '191', '2014-09-12 20:45:057', '86');
INSERT INTO `t_customerrecharge` VALUES ('179', '192', '2014-09-12 20:47:018', '23');
INSERT INTO `t_customerrecharge` VALUES ('180', '193', '2014-09-12 20:47:048', '8');
INSERT INTO `t_customerrecharge` VALUES ('181', '194', '2014-09-12 20:49:044', '47');
INSERT INTO `t_customerrecharge` VALUES ('182', '195', '2014-09-12 20:51:013', '84');
INSERT INTO `t_customerrecharge` VALUES ('183', '196', '2014-09-12 20:51:053', '384');
INSERT INTO `t_customerrecharge` VALUES ('184', '197', '2014-09-12 20:52:048', '6');
INSERT INTO `t_customerrecharge` VALUES ('185', '198', '2014-09-12 20:53:020', '33');
INSERT INTO `t_customerrecharge` VALUES ('186', '199', '2014-09-12 20:56:045', '256');
INSERT INTO `t_customerrecharge` VALUES ('187', '200', '2014-09-12 20:57:045', '29');
INSERT INTO `t_customerrecharge` VALUES ('188', '201', '2014-09-12 20:59:024', '55');

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
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci COMMENT='dataDictType\r\ncountCardType--计次卡类型\r\n';

-- ----------------------------
-- Records of t_datadict
-- ----------------------------
INSERT INTO `t_datadict` VALUES ('3', 'countCardType', null, '睫毛', null);
INSERT INTO `t_datadict` VALUES ('4', 'countCardType', null, '2323', null);
INSERT INTO `t_datadict` VALUES ('5', 'countCardType', null, '护理', null);
INSERT INTO `t_datadict` VALUES ('6', 'countCardType', null, '123', null);

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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES ('1', '脚搓板', '20', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=32 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_sale
-- ----------------------------
INSERT INTO `t_sale` VALUES ('7', '114', '1', '2014-09-12 00:00:35', '0', '30', '8', '24', '16', '24', '0');
INSERT INTO `t_sale` VALUES ('8', '88', '1', '2014-09-12 00:01:58', '0', '10', '8.8', '9', '107', '9', '0');
INSERT INTO `t_sale` VALUES ('9', '45', '1', '2014-09-12 00:03:02', '100', '0', '6', '100', '140', '100', '0');
INSERT INTO `t_sale` VALUES ('10', '69', '1', '2014-09-12 13:59:37', '0', '10', '8', '8', '193', '8', '0');
INSERT INTO `t_sale` VALUES ('11', '123', '1', '2014-09-12 14:02:43', '0', '120', '8', '96', '429', '96', '0');
INSERT INTO `t_sale` VALUES ('12', '86', '1', '2014-09-12 15:52:31', '228', '30', '8', '252', '181', '252', '0');
INSERT INTO `t_sale` VALUES ('13', '86', '1', '2014-09-12 16:04:04', '20', '0', '8', '20', '161', '20', '0');
INSERT INTO `t_sale` VALUES ('14', '163', '1', '2014-09-12 19:57:54', '0', '196', '8', '157', '138', '157', '0');
INSERT INTO `t_sale` VALUES ('15', '23', '1', '2014-09-12 20:09:03', '0', '320', '7', '224', '215', '224', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_saleitem
-- ----------------------------
INSERT INTO `t_saleitem` VALUES ('1', '光疗', '120', '1');
INSERT INTO `t_saleitem` VALUES ('2', '补睫毛', '100', '0');
INSERT INTO `t_saleitem` VALUES ('3', '修手', '10', '1');
INSERT INTO `t_saleitem` VALUES ('4', '可卸', '80', '1');
INSERT INTO `t_saleitem` VALUES ('5', '睫毛', '138', '1');
INSERT INTO `t_saleitem` VALUES ('6', '皇后睫毛', '380', '1');
INSERT INTO `t_saleitem` VALUES ('7', '饰品', '20', '1');
INSERT INTO `t_saleitem` VALUES ('8', '搓脚', '15', '0');
INSERT INTO `t_saleitem` VALUES ('9', '修脚', '20', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_saleitemdetail
-- ----------------------------
INSERT INTO `t_saleitemdetail` VALUES ('7', '7', '3', '1', '10', '1');
INSERT INTO `t_saleitemdetail` VALUES ('8', '7', '9', '1', '20', '1');
INSERT INTO `t_saleitemdetail` VALUES ('9', '8', '3', '1', '10', '1');
INSERT INTO `t_saleitemdetail` VALUES ('10', '9', '2', '1', '100', '0');
INSERT INTO `t_saleitemdetail` VALUES ('11', '10', '3', '1', '10', '1');
INSERT INTO `t_saleitemdetail` VALUES ('12', '11', '1', '1', '120', '1');
INSERT INTO `t_saleitemdetail` VALUES ('13', '12', '2', '1', '228', '0');
INSERT INTO `t_saleitemdetail` VALUES ('14', '12', '3', '1', '30', '1');
INSERT INTO `t_saleitemdetail` VALUES ('15', '13', '2', '1', '20', '0');
INSERT INTO `t_saleitemdetail` VALUES ('16', '14', '1', '1', '196', '1');
INSERT INTO `t_saleitemdetail` VALUES ('17', '15', '1', '1', '320', '1');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of t_userrole
-- ----------------------------
INSERT INTO `t_userrole` VALUES ('1', '1', '1');
