/*
 Navicat Premium Data Transfer

 Source Server         : ZhYP_Cloud
 Source Server Type    : MySQL
 Source Server Version : 50628
 Source Host           : sh-cdb-irwnt885.sql.tencentcdb.com:63832
 Source Schema         : test001

 Target Server Type    : MySQL
 Target Server Version : 50628
 File Encoding         : 65001

 Date: 20/11/2018 17:08:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config
-- ----------------------------
DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cron` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of config
-- ----------------------------
BEGIN;
INSERT INTO `config` VALUES (1, '0 0/2 * * * ?');
COMMIT;

-- ----------------------------
-- Table structure for customerinfoModel
-- ----------------------------
DROP TABLE IF EXISTS `customerinfoModel`;
CREATE TABLE `customerinfoModel` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '客户ID',
  `customer_identification` varchar(60) DEFAULT NULL COMMENT '纳税人识别号',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `customer_code` varchar(60) DEFAULT NULL COMMENT '客户代码',
  `customer_address` varchar(255) DEFAULT NULL COMMENT '客户地址',
  `customer_telephone` varchar(20) DEFAULT NULL COMMENT '客户电话',
  `customer_bank` varchar(255) DEFAULT NULL COMMENT '客户开户行',
  `customer_account` varchar(60) DEFAULT NULL COMMENT '客户开户行账号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=836 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customerinfoModel
-- ----------------------------
BEGIN;
INSERT INTO `customerinfoModel` VALUES (1, '91510106MA61TKN40K', '四川华泰川宇钢铁有限公司', 'schtcygtyxgs', '成都市金牛区金丰路6号成都量力钢材物流中心D区', '028-83151265', '成都银行金府路支行', '1001300000466743');
INSERT INTO `customerinfoModel` VALUES (2, '91510106MA61TKN40M', '四川远铭洋贸易有限公司', 'scymyhmyyxgs', '成都市金牛区金丰路6号成都量力钢材物流中心D区', '028-9999999', '中国工商银行金府路支行', '1001300000455732');
INSERT INTO `customerinfoModel` VALUES (3, '91510106MA61TKN99M', '环融亚创(成都)投资管理有限公司', 'hryctzglyxgs', '成都市金牛区金丰路88号环融亚创中心', '028-9998888', '中国人民银行金府路支行', '100130000048888');
INSERT INTO `customerinfoModel` VALUES (742, NULL, '成都市骆氏贸易有限公司', 'cdslsmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (743, NULL, '重庆卡内基商贸有限公司', 'Dcqknjsmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (744, NULL, '重庆千信国际贸易有限公司', '12cqqxgjmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (745, NULL, '重庆永丹实业有限公司', 'cqydsyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (746, NULL, '成都万春农牧机械有限公司', 'cdwcnmjxyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (747, NULL, '四川仁寿鑫龙水务建设有限公司', 'scrsxlswjsyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (748, NULL, '四川盛世众鑫钢铁贸易有限公司', 'scsszxgtmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (749, NULL, '成都市事事顺贸易有限公司', 'cdsssswzmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (750, NULL, '重庆吉和科庆商贸有限公司', 'cqjhkqsmyxgm', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (751, NULL, '彭州市雄华金属加工厂', 'pzsxhjsjgc', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (752, NULL, '贵州永顺景贸易有限公司', 'gzysjmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (753, NULL, '重庆源杰物资有限公司', 'cqyjwzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (754, NULL, '重庆兰友物资有限公司', 'cqslywzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (755, NULL, '贵州三津洋物资有限公司', 'gzsjywzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (756, NULL, '昆明劲龙镀锌产品有限公司', 'kmjldxcpyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (757, NULL, '云南燕发贸易有限公司', 'ynyfmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (758, NULL, '成都汇津源钢铁有限公司', 'cdhjygtyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (759, NULL, '德阳巨龙重工物资配套有限公司', 'dyjlzgwzptyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (760, NULL, '成都弘鑫明商贸有限公司', '01cdhxmsmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (761, NULL, '贵州福联实业有限公司', 'gzflsyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (762, NULL, '贵州鑫同创物资贸易有限公司', 'gzxtcwzmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (763, NULL, '重庆金若管道制造有限公司', 'cqjrgdzzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (764, NULL, '成都瑞凌物资有限公司', 'cdrlwzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (765, NULL, '成都恒大发物资有限公司', 'cdhdfwzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (766, NULL, '四川泰广钢铁有限公司', '8sctggtyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (767, NULL, '重庆勤耕钢管有限公司', 'cqqgggyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (768, NULL, '重庆源杰物资有限公司(角钢)', 'cqyjwzgxgs(jg)', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (769, NULL, '成都汇亿达贸易有限公司', '06cdhydmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (770, NULL, '成都名亿贸易有限公司', 'cdmymyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (771, NULL, '云南方管金属制品有限公司', 'ynfgjszpyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (772, NULL, '昆明贵冠商贸有限公司', 'kmggsmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (773, NULL, '成都鑫豪兴钢铁贸易有限公司', 'cdxhxgtmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (774, NULL, '重庆兰友物资有限公司（老库）', 'cqlywzyxgslk', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (775, NULL, '四川广中贸易有限公司', 'scgzmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (776, NULL, '重庆楚鸿物资有限公司', 'cqchwzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (777, NULL, '成都兴福鑫贸易有限公司', 'cdxfxmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (778, NULL, '遵义南华机电物资有限责任公司', 'zynhjdwzyxzrgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (779, NULL, '成都博程钢铁有限公司', 'cdbcgtyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (780, NULL, '成都市兴鸿源物资有限责任公司', 'cdsxhywzyxzrgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (781, NULL, '成都丰瑞辰贸易有限公司', 'cdfrcmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (782, NULL, '重庆攀腾商贸有限公司', '12cqptsmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (783, NULL, '成都林田贸易有限公司', 'cdltmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (784, NULL, '成都惠利诚贸易有限公司', 'cdhlcmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (785, NULL, '成都市利明丰商贸有限公司', '8cdslfsmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (786, NULL, '重庆凯建物资有限公司', 'cqkjwzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (787, NULL, '成都圆周贸易有限公司', 'cdyzmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (788, NULL, '汉中鑫泊炉料有限公司', 'hzxbllyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (789, NULL, '重庆龙润建筑安装工程有限公司', 'cqlrjzazgcyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (790, NULL, '毕节市汇鑫方舟钢材有限公司', '22bjshxfzgcyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (791, NULL, '成都惠宇达钢铁有限公司', 'cdhydgtyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (792, NULL, '重庆舰为商贸有限公司', 'cqjwsmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (793, NULL, '重庆天钐物资有限公司', 'cqtswzyxgs1', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (794, NULL, '遵义市源通机电有限公司', 'zysytjdyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (795, NULL, '六盘水汇鑫方舟钢材有限公司', 'lpshxfzgcyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (796, NULL, '四川天剑光电科技股份有限公司', 'sctjgdkjgfyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (797, NULL, '成都市铭发消防设备有限公司', 'cdsmfxfsbyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (798, NULL, '云南盛乾商贸有限公司', 'ynsqsmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (799, NULL, '成都正航贸易有限公司', 'cdzhmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (800, NULL, '重庆凯帝物资有限公司', 'cqkdwzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (801, NULL, '重庆千信国际贸易有限公司（铁公鸡）', '12cqqxgjmyyxgstgj', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (802, NULL, '贵州灿玲商贸有限公司', 'gzclsmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (803, NULL, '绵阳市安诚钢铁有限公司', 'mysacgtyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (804, NULL, '重庆千信国际贸易有限公司(铁公鸡)', 'Dcqqxgjmyyxgs(tgj)', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (805, NULL, '昆明燕发商贸有限公司', 'kmyfsmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (806, NULL, '成都市祥发物资有限公司', 'cdsxfwzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (807, NULL, '达州市宏升源物资有限公司', 'dzshsywzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (808, NULL, '达州市通川区昱鑫商贸有限公司', 'dzstcqyxsmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (809, NULL, '衡水鑫丰钢材有限公司', 'hsxfgcyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (810, NULL, '成都信德利物资有限公司', 'cdxdlwzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (811, NULL, '云南昌锴经贸有限公司', 'ynckjmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (812, NULL, '重庆汉邦物资有限公司(角钢)', 'cqhbwzyxgs(jg)', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (813, NULL, '巴中市华兴建筑有限公司', 'bzshxjzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (814, NULL, '四川省川汇塑胶有限公司', 'scchsjyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (815, NULL, '重庆卡内基商贸有限公司（铁公鸡）', 'Dcqknjsmyxgs(tgj)', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (816, NULL, '达州市红河谷物资有限公司', 'dzshhgwzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (817, NULL, '成都巨缘钢铁有限公司', 'cdjygtyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (818, NULL, '贵阳金顺泰物资有限公司', 'gyjstwzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (819, NULL, '重庆市匀豪物资有限公司', 'cqsyhwzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (820, NULL, '深圳深中孚建设工程有限公司', 'szszfjsgcyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (821, NULL, '绵阳嘉裕物资有限公司', 'myjywzyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (822, NULL, '成都闽锦商贸有限公司', 'cdmjsmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (823, NULL, '四川文正建设工程有限公司', 'scwzjsgcyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (824, NULL, '四川省江南玻璃钢有限公司', 'scsjnblgyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (825, NULL, '四川元硕钢铁有限公司', 'scysgtyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (826, NULL, '遵义丰毅源贸易有限公司', 'zyfyymyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (827, NULL, '四川双清螺旋钢管有限公司', 'scsqlxggyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (828, NULL, '达州市颜勇商贸有限公司', 'dzsyysmyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (829, NULL, '任丘市恒威通信设备有限公司', 'rqshwtxsbyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (830, NULL, '成都市鹏益欣贸易有限公司', '3cdspyxmyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (831, NULL, '重庆广鑫源贸易有限公司', 'cqgxymyyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (832, NULL, '四川正立消防科技有限责任公司攀枝花分公司', 'sczlxfgcyxzrgspzhfgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (833, NULL, '四川丹棱永恒消防工程有限公司', '8scdlyhxfgcyxgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (834, NULL, '遵义天诚消防工程有限责任公司', 'Ezytcxfgcyxzrgs', NULL, NULL, NULL, NULL);
INSERT INTO `customerinfoModel` VALUES (835, NULL, '达州市金龙物资有限公司', 'dzsjlwzyxgs', NULL, NULL, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for excelmodel
-- ----------------------------
DROP TABLE IF EXISTS `excelmodel`;
CREATE TABLE `excelmodel` (
  `col1` varchar(255) NOT NULL COMMENT '序号',
  `col2` varchar(255) DEFAULT NULL COMMENT '姓名',
  `col3` varchar(255) DEFAULT NULL COMMENT '性别',
  `col4` datetime DEFAULT NULL COMMENT '出生年月',
  `col5` varchar(2) DEFAULT NULL COMMENT '民族',
  `col6` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `col7` varchar(255) DEFAULT NULL COMMENT '文化程度',
  `col8` datetime DEFAULT NULL COMMENT '参工时间',
  `col9` varchar(20) DEFAULT NULL COMMENT '政治面貌',
  `col10` varchar(255) DEFAULT NULL COMMENT '职务',
  `col11` datetime DEFAULT NULL COMMENT '现处室时间',
  `col12` datetime DEFAULT NULL COMMENT '任现职位时间',
  `col13` datetime DEFAULT NULL COMMENT '任现职级时间',
  `col14` varchar(40) DEFAULT NULL COMMENT '职称',
  `col15` varchar(255) DEFAULT NULL COMMENT '执业资格',
  `col16` datetime DEFAULT NULL COMMENT '进局时间',
  `col17` varchar(40) DEFAULT NULL COMMENT '实务导师',
  `col18` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`col1`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for indentModel
-- ----------------------------
DROP TABLE IF EXISTS `indentModel`;
CREATE TABLE `indentModel` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `sale_date` varchar(20) DEFAULT NULL COMMENT '销售日期',
  `indent_number` varchar(40) DEFAULT NULL COMMENT '单号',
  `customer_code` varchar(255) DEFAULT NULL COMMENT '客户代码',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `purchase_quantity` decimal(9,2) DEFAULT NULL COMMENT '购货数量',
  `taxincluded_amount` decimal(9,2) DEFAULT NULL COMMENT '含税金额',
  `arrearages_goods` decimal(9,2) DEFAULT NULL COMMENT '仍欠货款',
  `zieher` varchar(40) DEFAULT NULL COMMENT '开票人',
  `is_generate_invoice` int(1) unsigned zerofill DEFAULT '0' COMMENT '是否已生成 0:未生成 1:已生成',
  `is_merge` int(1) unsigned zerofill DEFAULT '0' COMMENT '是否已合并 0:未合并 1:已合并',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4399 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for printModel
-- ----------------------------
DROP TABLE IF EXISTS `printModel`;
CREATE TABLE `printModel` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `generate_name` varchar(255) DEFAULT NULL COMMENT '名称',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称/ID号',
  `generate_Id` varchar(255) DEFAULT NULL COMMENT '生成发票内容ID号',
  `is_generate_invoice` varchar(20) DEFAULT '0' COMMENT '是否已生成发票，0：未生成发票 1：已生成发票',
  `is_print` varchar(20) DEFAULT '0' COMMENT '是否已打印生成的发票，0：未打印发票 1：已打印发票',
  `generate_date` varchar(20) DEFAULT NULL COMMENT '生成发票时间',
  `print_date` varchar(20) DEFAULT NULL COMMENT '更新打印的时间',
  `print_content` varchar(255) DEFAULT NULL COMMENT '打印内容的ID拼成的字符串',
  `invoice_amount` decimal(9,2) DEFAULT NULL COMMENT '发票总金额',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=119 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for productcodeModel
-- ----------------------------
DROP TABLE IF EXISTS `productcodeModel`;
CREATE TABLE `productcodeModel` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `specification_type` varchar(255) DEFAULT NULL COMMENT '商品编号类型',
  `spbm_code` varchar(19) DEFAULT '0' COMMENT '商品编号',
  `spbm_ version` varchar(10) DEFAULT NULL COMMENT '商品编号的版本号',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productcodeModel
-- ----------------------------
BEGIN;
INSERT INTO `productcodeModel` VALUES (1, '热镀', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (2, '焊管', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (3, '热镀锌国标精品管/红', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (4, 'QF热镀管', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (5, '方矩管', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (6, '热镀锌槽钢', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (7, '热镀锌角钢', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (8, '热镀锌管', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (9, '螺旋焊管', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (10, '热镀方矩管', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (13, '带钢', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (14, '直缝焊管', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (15, '热镀锌燃气专用管', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (16, '热镀锌衬塑复合管', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (17, '角钢', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (18, '涂塑复合管', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (19, '热镀锌角钢', '108020713', '30.0');
INSERT INTO `productcodeModel` VALUES (20, '燃气专用钢管', '108020713', '30.0');
COMMIT;

-- ----------------------------
-- Table structure for specificationModel
-- ----------------------------
DROP TABLE IF EXISTS `specificationModel`;
CREATE TABLE `specificationModel` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `indent_number` varchar(40) DEFAULT NULL COMMENT '单号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `specification` varchar(255) DEFAULT NULL COMMENT '规格',
  `sale_date` varchar(20) DEFAULT NULL COMMENT '销售日期',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `quantity` decimal(9,4) DEFAULT NULL COMMENT '数量',
  `measurement_unit` varchar(10) DEFAULT NULL COMMENT '计量单位',
  `taxincluded_price` decimal(9,2) DEFAULT NULL COMMENT '含税单价',
  `taxincluded_amount` decimal(9,2) DEFAULT NULL COMMENT '含税金额',
  `pre_tax` decimal(9,2) DEFAULT NULL COMMENT '税前金额',
  `taxes` decimal(9,2) DEFAULT NULL COMMENT '税金',
  `is_generate_invoice` int(1) unsigned zerofill DEFAULT '0' COMMENT ' 是否已生成发票，0：未生成发票 1：已生成发票',
  `is_print` int(1) unsigned zerofill DEFAULT '0' COMMENT ' 是否已生成发票，0：未打印发票 1：已打印发票',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2296 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for specificationModel_tmp
-- ----------------------------
DROP TABLE IF EXISTS `specificationModel_tmp`;
CREATE TABLE `specificationModel_tmp` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `indent_number` varchar(40) DEFAULT NULL COMMENT '单号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `specification` varchar(255) DEFAULT NULL COMMENT '规格',
  `sale_date` varchar(20) DEFAULT NULL COMMENT '销售日期',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `quantity` decimal(9,4) DEFAULT NULL COMMENT '数量',
  `measurement_unit` varchar(10) DEFAULT NULL COMMENT '计量单位',
  `taxincluded_price` decimal(9,2) DEFAULT NULL COMMENT '含税单价',
  `taxincluded_amount` decimal(9,2) DEFAULT NULL COMMENT '含税金额',
  `pre_tax` decimal(9,2) DEFAULT NULL COMMENT '税前金额',
  `taxes` decimal(9,2) DEFAULT NULL COMMENT '税金',
  `is_generate_invoice` int(1) unsigned zerofill DEFAULT '0' COMMENT ' 是否已生成发票，0：未生成发票 1：已生成发票',
  `is_print` int(1) unsigned zerofill DEFAULT '0' COMMENT ' 是否已生成发票，0：未打印发票 1：已打印发票',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=310 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for specification_merge
-- ----------------------------
DROP TABLE IF EXISTS `specification_merge`;
CREATE TABLE `specification_merge` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `indent_number` varchar(40) DEFAULT NULL COMMENT '单号',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `specification` varchar(255) DEFAULT NULL COMMENT '规格',
  `sale_date` varchar(20) DEFAULT NULL COMMENT '销售日期',
  `customer_name` varchar(255) DEFAULT NULL COMMENT '客户名称',
  `quantity` decimal(9,4) DEFAULT NULL COMMENT '数量',
  `measurement_unit` varchar(10) DEFAULT NULL COMMENT '计量单位',
  `taxincluded_price` decimal(9,2) DEFAULT NULL COMMENT '含税单价',
  `taxincluded_amount` decimal(9,2) DEFAULT NULL COMMENT '含税金额',
  `pre_tax` decimal(9,2) DEFAULT NULL COMMENT '税前金额',
  `taxes` decimal(9,2) DEFAULT NULL COMMENT '税金',
  `is_generate_invoice` int(1) unsigned zerofill DEFAULT '0' COMMENT ' 是否已生成发票，0：未生成发票 1：已生成发票',
  `is_print` int(1) unsigned zerofill DEFAULT '0' COMMENT ' 是否已生成发票，0：未打印发票 1：已打印发票',
  `spbm_code` varchar(19) DEFAULT NULL COMMENT '商品编码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1763 DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
