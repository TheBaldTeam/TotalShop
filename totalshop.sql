/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : totalshop

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-02-16 16:38:08
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '地址id',
  `address` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '地址详情',
  `user_id` int(20) DEFAULT NULL COMMENT '用户id',
  `user_name` varchar(200) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for class_with_product
-- ----------------------------
DROP TABLE IF EXISTS `class_with_product`;
CREATE TABLE `class_with_product` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `level2_class_id` int(20) DEFAULT NULL COMMENT '二级目录id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of class_with_product
-- ----------------------------
INSERT INTO `class_with_product` VALUES ('17', '31', '11');
INSERT INTO `class_with_product` VALUES ('18', '32', '12');
INSERT INTO `class_with_product` VALUES ('19', '33', '13');
INSERT INTO `class_with_product` VALUES ('20', '34', '20');
INSERT INTO `class_with_product` VALUES ('21', '35', '11');
INSERT INTO `class_with_product` VALUES ('22', '36', '12');
INSERT INTO `class_with_product` VALUES ('23', '37', '13');
INSERT INTO `class_with_product` VALUES ('24', '38', '14');

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `user_id` int(20) DEFAULT NULL,
  `seller_id` int(20) DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect` VALUES ('1', '33', '21', '31');
INSERT INTO `collect` VALUES ('2', '33', null, '33');

-- ----------------------------
-- Table structure for product_img
-- ----------------------------
DROP TABLE IF EXISTS `product_img`;
CREATE TABLE `product_img` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `is_cover` int(20) DEFAULT '0' COMMENT '是否封面',
  PRIMARY KEY (`id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `product_img_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product_item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_img
-- ----------------------------
INSERT INTO `product_img` VALUES ('57', '31', '28dcdf43-ab8f-4383-b88d-15e3f74f87d6.png', '1');
INSERT INTO `product_img` VALUES ('58', '31', '513e34dc-e583-434d-92a9-ea39094219bc.png', '0');
INSERT INTO `product_img` VALUES ('59', '32', '128d76d2-0e8d-4620-8fcb-f6dac93499fb.png', '1');
INSERT INTO `product_img` VALUES ('60', '32', '30e2f89a-91e6-4e40-aa6b-591594e04f91.png', '0');
INSERT INTO `product_img` VALUES ('61', '33', '38a882d5-8660-4d0f-bd1f-0b8c53e653b9.png', '0');
INSERT INTO `product_img` VALUES ('62', '33', 'a194cd72-ae2e-4ad9-8cf7-036d6a410536.png', '1');
INSERT INTO `product_img` VALUES ('63', '34', 'd88c1aab-ac29-41f9-96ee-42255af184fb.jpg', '0');
INSERT INTO `product_img` VALUES ('64', '34', 'e5a866a3-0bba-4ea8-9855-2e127b4856c2.png', '1');
INSERT INTO `product_img` VALUES ('65', '35', '58f324f5-1018-4338-a2ca-675dbe682c13.png', '1');
INSERT INTO `product_img` VALUES ('66', '35', '63c122e6-d9a9-4cb6-8a3b-df01735604a5.png', '0');

-- ----------------------------
-- Table structure for product_item
-- ----------------------------
DROP TABLE IF EXISTS `product_item`;
CREATE TABLE `product_item` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '商品id，同时也是商品编号',
  `title` varchar(100) DEFAULT NULL COMMENT '商品标题',
  `sell_point` varchar(500) DEFAULT NULL COMMENT '商品卖点',
  `price` decimal(20,2) DEFAULT NULL COMMENT '商品价格，单位为：角',
  `group_price` decimal(20,2) DEFAULT NULL COMMENT '商品拼团价格，单位为：角',
  `is_group` int(20) DEFAULT NULL,
  `num` int(10) DEFAULT NULL COMMENT '库存数量',
  `cid` int(10) DEFAULT NULL COMMENT '所属类目，叶子类目',
  `status` tinyint(4) DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，3-删除',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '更新时间',
  `sales_volume` int(255) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `cid` (`cid`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `updated` (`updated`) USING BTREE,
  CONSTRAINT `product_item_ibfk_1` FOREIGN KEY (`cid`) REFERENCES `shop_classify` (`class_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品表';

-- ----------------------------
-- Records of product_item
-- ----------------------------
INSERT INTO `product_item` VALUES ('31', '商家1的商品1', null, '22.00', '22.00', '1', '22', '11', '1', '2019-02-16 10:36:40', '2019-02-16 10:36:40', '0');
INSERT INTO `product_item` VALUES ('32', '商家1的商品2', null, '22.00', null, '0', '22', '12', '1', '2019-02-16 10:37:22', '2019-02-16 10:37:22', '0');
INSERT INTO `product_item` VALUES ('33', '商家1的商品3', null, '33.00', null, '0', '33', '13', '1', '2019-02-16 10:38:41', '2019-02-16 10:38:41', '0');
INSERT INTO `product_item` VALUES ('34', '商家1的商品4', null, '44.00', '33.00', '1', '44', '20', '1', '2019-02-16 10:39:15', '2019-02-16 10:39:15', '0');
INSERT INTO `product_item` VALUES ('35', '商家2的商品1', null, '22.00', '22.00', '1', '321', '11', '1', '2019-02-16 11:09:51', '2019-02-16 11:09:51', '0');
INSERT INTO `product_item` VALUES ('36', '商家2的商品1', null, '22.00', '22.00', '1', '321', '12', '1', '2019-02-16 11:10:52', '2019-02-16 11:10:52', '0');
INSERT INTO `product_item` VALUES ('37', '商家2的商品3', null, '22.00', '22.00', '1', '321', '13', '1', '2019-02-16 11:11:14', '2019-02-16 11:11:14', '0');
INSERT INTO `product_item` VALUES ('38', '商家2的商品4', null, '22.00', '22.00', '1', '321', '14', '1', '2019-02-16 11:11:20', '2019-02-16 11:11:20', '0');

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '商户id',
  `tel` bigint(50) DEFAULT NULL COMMENT '商户电话',
  `title_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺名字',
  `user_id` int(20) DEFAULT NULL COMMENT '用户id',
  `seller_class` varchar(225) COLLATE utf8_bin DEFAULT '',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `user_id` (`user_id`),
  KEY `title_name` (`title_name`),
  CONSTRAINT `seller_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of seller
-- ----------------------------
INSERT INTO `seller` VALUES ('21', '1', '1', '33', '连锁超市');
INSERT INTO `seller` VALUES ('22', '2', '2', '34', '便利店');

-- ----------------------------
-- Table structure for seller_address
-- ----------------------------
DROP TABLE IF EXISTS `seller_address`;
CREATE TABLE `seller_address` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '商家地址id',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '商家地址',
  `seller_id` int(20) DEFAULT NULL COMMENT '商家id',
  `seller_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '店长名字',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `seller_id` (`seller_id`),
  CONSTRAINT `seller_address_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of seller_address
-- ----------------------------
INSERT INTO `seller_address` VALUES ('27', '1', '21', '1');
INSERT INTO `seller_address` VALUES ('28', '2', '22', '2');

-- ----------------------------
-- Table structure for seller_bcimg
-- ----------------------------
DROP TABLE IF EXISTS `seller_bcimg`;
CREATE TABLE `seller_bcimg` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '证件id',
  `img` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '证件路径',
  `user_id` int(20) DEFAULT NULL COMMENT '用户id',
  `seller_id` int(20) DEFAULT NULL COMMENT '商家id',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `seller_id` (`seller_id`),
  CONSTRAINT `seller_bcimg_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of seller_bcimg
-- ----------------------------
INSERT INTO `seller_bcimg` VALUES ('19', '19e3e5b8-5e56-473a-854e-fcf3d7ef97ce.jpg', '33', '21');
INSERT INTO `seller_bcimg` VALUES ('20', '3f4df1dd-d218-4a28-b3c9-d5af40d5bfd0.png', '34', '22');

-- ----------------------------
-- Table structure for seller_with_product_img
-- ----------------------------
DROP TABLE IF EXISTS `seller_with_product_img`;
CREATE TABLE `seller_with_product_img` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `seller_id` int(20) DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `seller_id` (`seller_id`),
  CONSTRAINT `seller_with_product_img_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `seller` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seller_with_product_img
-- ----------------------------
INSERT INTO `seller_with_product_img` VALUES ('17', '21', '31');
INSERT INTO `seller_with_product_img` VALUES ('18', '21', '32');
INSERT INTO `seller_with_product_img` VALUES ('19', '21', '33');
INSERT INTO `seller_with_product_img` VALUES ('20', '21', '34');
INSERT INTO `seller_with_product_img` VALUES ('21', '22', '35');
INSERT INTO `seller_with_product_img` VALUES ('22', '22', '36');
INSERT INTO `seller_with_product_img` VALUES ('23', '22', '37');
INSERT INTO `seller_with_product_img` VALUES ('24', '22', '38');

-- ----------------------------
-- Table structure for shop_classify
-- ----------------------------
DROP TABLE IF EXISTS `shop_classify`;
CREATE TABLE `shop_classify` (
  `class_id` int(11) NOT NULL AUTO_INCREMENT,
  `class_name` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `class_key` int(11) DEFAULT NULL COMMENT '外键',
  `level` int(11) DEFAULT NULL COMMENT '1一级目录  2二级目录',
  `ondelect` int(11) DEFAULT '0' COMMENT '显示 0显示，1不显示',
  `images` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`class_id`) USING BTREE,
  KEY `fk_id` (`class_key`) USING BTREE,
  CONSTRAINT `fk_id` FOREIGN KEY (`class_key`) REFERENCES `shop_classify` (`class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=74 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT COMMENT='分类表';

-- ----------------------------
-- Records of shop_classify
-- ----------------------------
INSERT INTO `shop_classify` VALUES ('1', '服装饰品', null, '1', '0', null);
INSERT INTO `shop_classify` VALUES ('2', '鞋包配件', null, '1', '0', null);
INSERT INTO `shop_classify` VALUES ('3', '电器玩具', null, '1', '0', null);
INSERT INTO `shop_classify` VALUES ('4', '零食生鲜', null, '1', '1', null);
INSERT INTO `shop_classify` VALUES ('5', '百货家具', null, '1', '1', null);
INSERT INTO `shop_classify` VALUES ('6', '家电数码', null, '1', '0', null);
INSERT INTO `shop_classify` VALUES ('7', '办公五金', null, '1', '1', null);
INSERT INTO `shop_classify` VALUES ('8', '户外乐器', null, '1', '1', null);
INSERT INTO `shop_classify` VALUES ('9', '美妆洗护', null, '1', '1', null);
INSERT INTO `shop_classify` VALUES ('10', '游戏影视', null, '1', '1', null);
INSERT INTO `shop_classify` VALUES ('11', '男装', '1', '2', '0', 'nanxie.png');
INSERT INTO `shop_classify` VALUES ('12', '女装', '1', '2', '0', 'lianyiqun.png');
INSERT INTO `shop_classify` VALUES ('13', '羽绒服', '1', '2', '0', 'yurongfu.png');
INSERT INTO `shop_classify` VALUES ('14', '棉服', '1', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('15', '大衣夹克', '1', '2', '0', 'jiake.png');
INSERT INTO `shop_classify` VALUES ('16', '卫衣', '1', '2', '0', 'weiyi.png');
INSERT INTO `shop_classify` VALUES ('17', '牛仔裤', '1', '2', '0', 'niuziku.png');
INSERT INTO `shop_classify` VALUES ('18', '休闲裤', '1', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('19', '衬衫', '1', '2', '0', 'chenshan.png');
INSERT INTO `shop_classify` VALUES ('20', '女鞋', '2', '2', '0', 'nvxie.png');
INSERT INTO `shop_classify` VALUES ('21', '男鞋', '2', '2', '0', 'nanxie.png');
INSERT INTO `shop_classify` VALUES ('22', '靴子', '2', '2', '0', 'xiezi.png');
INSERT INTO `shop_classify` VALUES ('23', '低帮鞋', '2', '2', '0', 'xiezi_1.png');
INSERT INTO `shop_classify` VALUES ('24', '高帮鞋', '2', '2', '0', 'chaoyipu.png');
INSERT INTO `shop_classify` VALUES ('25', '皮鞋', '2', '2', '0', 'pixie.png');
INSERT INTO `shop_classify` VALUES ('26', '帆布鞋', '2', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('27', '箱包皮具', '2', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('28', '双肩包', '2', '2', '0', 'shuangjianbao.png');
INSERT INTO `shop_classify` VALUES ('29', '饭锅电磁炉', '3', '2', '0', 'dianfanguo.png');
INSERT INTO `shop_classify` VALUES ('30', '取暖电器', '3', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('31', '洗衣机', '3', '2', '0', 'xiyiji.png');
INSERT INTO `shop_classify` VALUES ('32', '厨房电器', '3', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('33', '个护健康', '3', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('34', '冰箱空调', '3', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('35', '生活电器', '3', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('36', '电视', '3', '2', '0', 'dianshi.png');
INSERT INTO `shop_classify` VALUES ('37', '烟灶热水器', '3', '2', '0', 'reshuiqi.png');
INSERT INTO `shop_classify` VALUES ('38', '休闲零食', '4', '2', '1', 'xiuxianlingshi.png');
INSERT INTO `shop_classify` VALUES ('39', '乳制品', '4', '2', '1', 'tubiao.png');
INSERT INTO `shop_classify` VALUES ('40', '饼干糕点', '4', '2', '1', 'gaodian.png');
INSERT INTO `shop_classify` VALUES ('41', '茶叶冲饮', '4', '2', '1', 'chaye.png');
INSERT INTO `shop_classify` VALUES ('42', '速食', '4', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('43', '中外名酒', '4', '2', '1', 'jiu.png');
INSERT INTO `shop_classify` VALUES ('44', '新鲜水果', '4', '2', '1', 'shuiguo.png');
INSERT INTO `shop_classify` VALUES ('45', '进口优选', '4', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('46', '海鲜水产', '4', '2', '1', 'haixian.png');
INSERT INTO `shop_classify` VALUES ('47', '床和床垫', '5', '2', '1', 'chuang.png');
INSERT INTO `shop_classify` VALUES ('48', '沙发电视柜', '5', '2', '1', 'shafa.png');
INSERT INTO `shop_classify` VALUES ('49', '餐桌电脑桌', '5', '2', '1', 'fanzhuo.png');
INSERT INTO `shop_classify` VALUES ('50', '柜子架子', '5', '2', '1', 'jiazi.png');
INSERT INTO `shop_classify` VALUES ('51', '椅子凳子', '5', '2', '1', 'yizi.png');
INSERT INTO `shop_classify` VALUES ('52', '儿童家具', '5', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('53', '办公家具', '5', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('54', '户外家具', '5', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('55', '学习桌', '5', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('56', '平板电脑', '6', '2', '0', 'pingbandiannao.png');
INSERT INTO `shop_classify` VALUES ('57', '学习机', '6', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('58', '电脑整机', '6', '2', '0', 'icon-test.png');
INSERT INTO `shop_classify` VALUES ('59', '笔类', '6', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('60', '外设配件', '6', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('61', '学生文具', '6', '2', '0', 'wenjuwujin.png');
INSERT INTO `shop_classify` VALUES ('62', '游戏设备', '6', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('63', '办公设备', '6', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('64', '网络设备', '6', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('65', '面部护理', '9', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('66', '面膜', '9', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('67', '彩妆香氛', '9', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('68', '美发护发', '9', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('69', '口红唇膏', '9', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('70', '水乳面霜', '9', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('71', '护手霜', '9', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('72', '个人洗护', '9', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('73', '美妆工具', '9', '2', '1', null);

-- ----------------------------
-- Table structure for shop_order
-- ----------------------------
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_sn` varchar(50) DEFAULT NULL COMMENT '订单编号',
  `add_time` datetime DEFAULT NULL COMMENT '添加时间',
  `user_name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `user_address` varchar(200) DEFAULT NULL COMMENT '用户地址',
  `tel` bigint(50) DEFAULT NULL COMMENT '用户电话',
  `
user_id` int(11) DEFAULT NULL COMMENT '用户Id',
  `seller_id` int(11) DEFAULT NULL,
  `seller_name` varchar(225) DEFAULT NULL,
  `total_money` decimal(12,2) DEFAULT NULL COMMENT '总金额',
  `mark` varchar(225) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_order
-- ----------------------------

-- ----------------------------
-- Table structure for shop_order_goods
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_goods`;
CREATE TABLE `shop_order_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goods_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL COMMENT '商品上架时间',
  `goods_name` varchar(200) DEFAULT NULL,
  `is_group` int(10) DEFAULT NULL,
  `price` decimal(12,2) DEFAULT NULL,
  `group_price` decimal(12,2) DEFAULT NULL,
  `go_num` int(11) DEFAULT NULL COMMENT '购买数量',
  `total_price` decimal(12,2) DEFAULT NULL COMMENT '总价',
  `order_id` int(11) DEFAULT NULL COMMENT '订单表id',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_order_goods
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT '用户名',
  `password` varchar(500) COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `is_vip` int(10) DEFAULT '0' COMMENT '是否为vip',
  `is_seller` int(20) DEFAULT '0' COMMENT '是否为商家',
  `tel` bigint(50) DEFAULT NULL COMMENT '手机号码',
  `score` int(20) DEFAULT '0' COMMENT '积分',
  `money` decimal(20,0) unsigned DEFAULT '0' COMMENT '用户余额',
  `is_admin` int(20) DEFAULT '0' COMMENT '是否为管理员',
  `is_apply` int(20) DEFAULT '0' COMMENT '是否提交商家注册',
  `apply_money` decimal(50,0) DEFAULT '0' COMMENT '申请金额',
  `applied_mark` varchar(255) COLLATE utf8_bin DEFAULT '' COMMENT '申请后返回备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('33', '1', 'c4ca4238a0b923820dcc509a6f75849b', '0', '1', '1', '0', '0', '1', '1', '0', '');
INSERT INTO `user` VALUES ('34', '2', 'c81e728d9d4c2f636f067f89cc14862c', '0', '1', '2', '0', '0', '0', '1', '0', '');

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '型号id',
  `product_id` int(20) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名字',
  `version_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '型号名字',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `product_id` (`product_id`),
  CONSTRAINT `version_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product_item` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of version
-- ----------------------------
INSERT INTO `version` VALUES ('41', '31', '商家1的商品1', '122');
INSERT INTO `version` VALUES ('42', '32', '商家1的商品2', '22');
INSERT INTO `version` VALUES ('43', '33', '商家1的商品3', '33');
INSERT INTO `version` VALUES ('44', '34', '商家1的商品4', '44');
INSERT INTO `version` VALUES ('45', '35', '商家2的商品1', '22');
INSERT INTO `version` VALUES ('46', '36', '商家2的商品1', '22');
INSERT INTO `version` VALUES ('47', '37', '商家2的商品3', '22');
INSERT INTO `version` VALUES ('48', '38', '商家2的商品4', '22');
