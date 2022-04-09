/*
 Navicat Premium Data Transfer

 Source Server         : 番组
 Source Server Type    : MySQL
 Source Server Version : 50650
 Source Host           : 43.138.25.60:3306
 Source Schema         : bangumi

 Target Server Type    : MySQL
 Target Server Version : 50650
 File Encoding         : 65001

 Date: 09/04/2022 22:52:32
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bangumi
-- ----------------------------
DROP TABLE IF EXISTS `bangumi`;
CREATE TABLE `bangumi` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `title` varchar(255) NOT NULL COMMENT '番剧日文名称',
  `type` varchar(255) DEFAULT NULL COMMENT '番剧类型',
  `lang` varchar(255) DEFAULT NULL COMMENT '番组语言',
  `official_site` varchar(255) DEFAULT NULL COMMENT '官网',
  `end_flag` int(1) DEFAULT NULL COMMENT '结束标记，0表示还没有结束1表示结束了',
  `begin` varchar(255) DEFAULT NULL COMMENT 'tv/web：番组开始时间；movie：上映日期；ova：首话发售时间 [required]',
  `end` varchar(255) DEFAULT NULL COMMENT 'tv/web：番组完结时间；movie：无意义；ova：则为最终话发售时间（未确定则置空） [required]',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用于存储番剧基本信息';

-- ----------------------------
-- Table structure for site
-- ----------------------------
DROP TABLE IF EXISTS `site`;
CREATE TABLE `site` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `bangumi_id` int(11) DEFAULT NULL COMMENT '番剧id',
  `type` varchar(255) DEFAULT NULL COMMENT '站点类型',
  `site` varchar(255) DEFAULT NULL COMMENT '站点 name，请和最外层 sites 字段中的元数据对应 [required]',
  `site_id` int(11) DEFAULT NULL COMMENT '站点 id，可用于替换模板中相应的字段',
  `url` varchar(255) DEFAULT NULL COMMENT 'url，如果当前url不符合urlTemplate中的规则时使用，优先级高于id',
  `comment` varchar(255) DEFAULT NULL COMMENT '备注',
  `regions` varchar(255) DEFAULT NULL COMMENT '区域限制',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='每一步番剧的站点数据';

-- ----------------------------
-- Table structure for site_meta
-- ----------------------------
DROP TABLE IF EXISTS `site_meta`;
CREATE TABLE `site_meta` (
  `name` varchar(255) NOT NULL COMMENT '站点字段名',
  `title` varchar(255) DEFAULT NULL COMMENT '站点名称',
  `url_template` varchar(255) DEFAULT NULL COMMENT '站点url模版',
  `regions` varchar(255) DEFAULT NULL COMMENT '站点区域限制，主要针对onAir类型的放送站点。如无该字段，表明该站点无区域限制',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='站点元数据';

-- ----------------------------
-- Table structure for trans_title
-- ----------------------------
DROP TABLE IF EXISTS `trans_title`;
CREATE TABLE `trans_title` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `bangumi_id` int(11) DEFAULT NULL COMMENT '番剧id',
  `trans_type` varchar(50) DEFAULT NULL COMMENT '翻译类型',
  `trans_title` varchar(255) DEFAULT NULL COMMENT '翻译名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='番剧名称的翻译名';

SET FOREIGN_KEY_CHECKS = 1;
