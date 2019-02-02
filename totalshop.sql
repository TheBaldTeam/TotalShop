/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50722
Source Host           : localhost:3306
Source Database       : totalshop

Target Server Type    : MYSQL
Target Server Version : 50722
File Encoding         : 65001

Date: 2019-02-02 08:50:35
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
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of address
-- ----------------------------

-- ----------------------------
-- Table structure for product_img
-- ----------------------------
DROP TABLE IF EXISTS `product_img`;
CREATE TABLE `product_img` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `product_id` int(20) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `is_cover` int(20) DEFAULT '0' COMMENT '是否封面',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_img
-- ----------------------------
INSERT INTO `product_img` VALUES ('1', '1', 'qwewera.png', '1');
INSERT INTO `product_img` VALUES ('2', '1', 'erqwer.png', '0');
INSERT INTO `product_img` VALUES ('3', '1', 'asdfasd.png', '0');
INSERT INTO `product_img` VALUES ('4', '1', 'asdfaawerqwer.png', '0');
INSERT INTO `product_img` VALUES ('5', '2', '23434.png', '1');
INSERT INTO `product_img` VALUES ('6', '2', '123.png', '0');
INSERT INTO `product_img` VALUES ('7', '3', '123123.png', '0');
INSERT INTO `product_img` VALUES ('8', '9', 'ebca6f53-376e-4f26-81d8-af98e6595dc0.png', '0');
INSERT INTO `product_img` VALUES ('9', '9', '19ae784e-8b82-40c8-8eee-fb32efb97f04.png', '1');
INSERT INTO `product_img` VALUES ('10', '10', '940ce01f-df82-40e6-81dc-f594e143aa71.png', '1');
INSERT INTO `product_img` VALUES ('11', '10', 'ec966960-ff12-4a33-a221-339b01ad3b61.png', '0');
INSERT INTO `product_img` VALUES ('12', '11', '3f3a5271-807b-433b-b596-1acf4fab5ee6.png', '0');
INSERT INTO `product_img` VALUES ('13', '11', 'a25a62f2-281b-4fd9-a9f8-577bdc605a25.png', '1');
INSERT INTO `product_img` VALUES ('14', '12', 'c0d646e8-92b9-4d2a-be17-ec1ae9460409.png', '1');
INSERT INTO `product_img` VALUES ('15', '12', '5d09f8b1-6b5f-4ecf-960e-191f21785dc6.png', '0');
INSERT INTO `product_img` VALUES ('16', '13', 'd1dbda65-0a77-4cbd-ab82-7b001fa0e49c.png', '1');
INSERT INTO `product_img` VALUES ('17', '13', 'd3f6508b-1013-488d-97fb-1f1849f95e8f.png', '0');
INSERT INTO `product_img` VALUES ('18', '14', '98a96063-41ab-4792-a0c7-dec532ce06e3.png', '1');
INSERT INTO `product_img` VALUES ('19', '14', 'c837a471-05e2-491c-b590-e7def86a55c4.png', '0');

-- ----------------------------
-- Table structure for product_item
-- ----------------------------
DROP TABLE IF EXISTS `product_item`;
CREATE TABLE `product_item` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '商品id，同时也是商品编号',
  `title` varchar(100) DEFAULT NULL COMMENT '商品标题',
  `sell_point` varchar(500) DEFAULT NULL COMMENT '商品卖点',
  `price` int(20) DEFAULT NULL COMMENT '商品价格，单位为：角',
  `group_price` int(20) DEFAULT NULL COMMENT '商品拼团价格，单位为：角',
  `is_group` int(20) DEFAULT NULL,
  `num` int(10) DEFAULT NULL COMMENT '库存数量',
  `cid` int(10) DEFAULT NULL COMMENT '所属类目，叶子类目',
  `status` tinyint(4) DEFAULT '1' COMMENT '商品状态，1-正常，2-下架，3-删除',
  `created` datetime DEFAULT NULL COMMENT '创建时间',
  `updated` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `cid` (`cid`) USING BTREE,
  KEY `status` (`status`) USING BTREE,
  KEY `updated` (`updated`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='商品表';

-- ----------------------------
-- Records of product_item
-- ----------------------------
INSERT INTO `product_item` VALUES ('1', '小清新手账本活页本笔记本子简约大学生记事本', null, '43', '0', '0', '0', '1', '1', '0000-00-00 00:00:00', '0000-00-00 00:00:00');
INSERT INTO `product_item` VALUES ('2', '商品测试2', null, '40', '0', '0', '0', '1', '1', '2019-01-29 17:15:00', '2019-01-29 17:15:03');
INSERT INTO `product_item` VALUES ('3', '321', null, '321', '321', '1', '21', '11', '1', '2019-01-30 15:57:58', '2019-01-30 15:57:58');
INSERT INTO `product_item` VALUES ('4', '321', null, '321', '321', '1', '21', '11', '1', '2019-01-30 16:00:29', '2019-01-30 16:00:29');
INSERT INTO `product_item` VALUES ('5', '321', null, '312', '312', '1', '321', '11', '1', '2019-01-30 16:03:03', '2019-01-30 16:03:03');
INSERT INTO `product_item` VALUES ('6', '321', null, '312', '321', '1', '321', '11', '1', '2019-01-30 16:07:54', '2019-01-30 16:07:54');
INSERT INTO `product_item` VALUES ('7', '321', null, '312', '321', '1', '312', '11', '1', '2019-01-30 16:08:54', '2019-01-30 16:08:54');
INSERT INTO `product_item` VALUES ('8', '321', null, '312', '321', '1', '321', '11', '1', '2019-01-30 16:11:08', '2019-01-30 16:11:08');
INSERT INTO `product_item` VALUES ('9', '321', null, '312', '321', '1', '312', '11', '1', '2019-01-30 16:13:41', '2019-01-30 16:13:41');
INSERT INTO `product_item` VALUES ('10', '321', null, '312', '321', '1', '312', '11', '1', '2019-01-30 16:18:19', '2019-01-30 16:18:19');
INSERT INTO `product_item` VALUES ('11', '321', null, '321', '321', '1', '312', '11', '1', '2019-01-30 16:33:37', '2019-01-30 16:33:37');
INSERT INTO `product_item` VALUES ('12', '321', null, '321', '321', '1', '21', '11', '1', '2019-01-30 16:38:17', '2019-01-30 16:38:17');
INSERT INTO `product_item` VALUES ('13', '321', null, '312', '321', '1', '21', '11', '1', '2019-01-30 16:41:19', '2019-01-30 16:41:19');
INSERT INTO `product_item` VALUES ('14', '321', null, '321', '321', '1', '312', '11', '1', '2019-01-30 16:43:34', '2019-01-30 16:43:34');

-- ----------------------------
-- Table structure for seller
-- ----------------------------
DROP TABLE IF EXISTS `seller`;
CREATE TABLE `seller` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '商户id',
  `tel` bigint(50) DEFAULT NULL COMMENT '商户电话',
  `title_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '店铺名字',
  `user_id` int(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of seller
-- ----------------------------
INSERT INTO `seller` VALUES ('6', '123', '123', '28');
INSERT INTO `seller` VALUES ('9', '321', '312', '27');

-- ----------------------------
-- Table structure for seller_address
-- ----------------------------
DROP TABLE IF EXISTS `seller_address`;
CREATE TABLE `seller_address` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '商家地址id',
  `address` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '商家地址',
  `seller_id` int(20) DEFAULT NULL COMMENT '商家id',
  `seller_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '店长名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of seller_address
-- ----------------------------
INSERT INTO `seller_address` VALUES ('12', '123', '6', '123');
INSERT INTO `seller_address` VALUES ('15', '321', '9', '1');

-- ----------------------------
-- Table structure for seller_bcimg
-- ----------------------------
DROP TABLE IF EXISTS `seller_bcimg`;
CREATE TABLE `seller_bcimg` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '证件id',
  `img` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '证件路径',
  `user_id` int(20) DEFAULT NULL COMMENT '用户id',
  `seller_id` int(20) DEFAULT NULL COMMENT '商家id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of seller_bcimg
-- ----------------------------
INSERT INTO `seller_bcimg` VALUES ('7', '7a0ec9fa-6f6b-4e3b-a5ef-5d36e1f28d53.png', '28', '6');
INSERT INTO `seller_bcimg` VALUES ('10', 'e457cb24-0e22-4202-af82-e49fc151799b.png', '27', '9');

-- ----------------------------
-- Table structure for seller_with_product_img
-- ----------------------------
DROP TABLE IF EXISTS `seller_with_product_img`;
CREATE TABLE `seller_with_product_img` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `seller_id` int(20) DEFAULT NULL,
  `product_id` int(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of seller_with_product_img
-- ----------------------------

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
INSERT INTO `shop_classify` VALUES ('4', '零食生鲜', null, '1', '0', null);
INSERT INTO `shop_classify` VALUES ('5', '百货家具', null, '1', '0', null);
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
INSERT INTO `shop_classify` VALUES ('38', '休闲零食', '4', '2', '0', 'xiuxianlingshi.png');
INSERT INTO `shop_classify` VALUES ('39', '乳制品', '4', '2', '0', 'tubiao.png');
INSERT INTO `shop_classify` VALUES ('40', '饼干糕点', '4', '2', '0', 'gaodian.png');
INSERT INTO `shop_classify` VALUES ('41', '茶叶冲饮', '4', '2', '0', 'chaye.png');
INSERT INTO `shop_classify` VALUES ('42', '速食', '4', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('43', '中外名酒', '4', '2', '0', 'jiu.png');
INSERT INTO `shop_classify` VALUES ('44', '新鲜水果', '4', '2', '0', 'shuiguo.png');
INSERT INTO `shop_classify` VALUES ('45', '进口优选', '4', '2', '1', null);
INSERT INTO `shop_classify` VALUES ('46', '海鲜水产', '4', '2', '0', 'haixian.png');
INSERT INTO `shop_classify` VALUES ('47', '床和床垫', '5', '2', '0', 'chuang.png');
INSERT INTO `shop_classify` VALUES ('48', '沙发电视柜', '5', '2', '0', 'shafa.png');
INSERT INTO `shop_classify` VALUES ('49', '餐桌电脑桌', '5', '2', '0', 'fanzhuo.png');
INSERT INTO `shop_classify` VALUES ('50', '柜子架子', '5', '2', '0', 'jiazi.png');
INSERT INTO `shop_classify` VALUES ('51', '椅子凳子', '5', '2', '0', 'yizi.png');
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
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('27', '1', 'c4ca4238a0b923820dcc509a6f75849b', '0', '1', '1', '0', '0', '1', '1', '0', '');
INSERT INTO `user` VALUES ('28', '123', '202cb962ac59075b964b07152d234b70', '0', '1', '123', '0', '0', '0', '1', '0', '');
INSERT INTO `user` VALUES ('29', '2', 'c81e728d9d4c2f636f067f89cc14862c', '0', '0', '2', '0', '0', '0', '0', '0', '');

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '型号id',
  `product_id` int(20) DEFAULT NULL COMMENT '商品id',
  `product_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '商品名字',
  `version_name` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '型号名字',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of version
-- ----------------------------
INSERT INTO `version` VALUES ('1', '4', '321', '321');
INSERT INTO `version` VALUES ('2', '4', '321', '544');
INSERT INTO `version` VALUES ('3', '5', '321', '312');
INSERT INTO `version` VALUES ('4', '6', '321', '321');
INSERT INTO `version` VALUES ('5', '7', '321', '312');
INSERT INTO `version` VALUES ('6', '8', '321', '321');
INSERT INTO `version` VALUES ('7', '9', '321', '321');
INSERT INTO `version` VALUES ('8', '10', '321', '321');
INSERT INTO `version` VALUES ('9', '11', '321', '321');
INSERT INTO `version` VALUES ('10', '12', '321', '321');
INSERT INTO `version` VALUES ('11', '12', '321', '45543');
INSERT INTO `version` VALUES ('12', '13', '321', '321');
INSERT INTO `version` VALUES ('13', '14', '321', '321');
