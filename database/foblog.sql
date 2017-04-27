/*
Navicat MySQL Data Transfer

Source Server         : 120.76.250.153
Source Server Version : 50634
Source Host           : 120.76.250.153:3306
Source Database       : foblog

Target Server Type    : MYSQL
Target Server Version : 50634
File Encoding         : 65001

Date: 2017-04-27 11:05:30
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(70) NOT NULL COMMENT '标题',
  `summary` varchar(600) DEFAULT NULL COMMENT '概要',
  `content` text COMMENT '内容',
  `category_ids` varchar(20) DEFAULT NULL COMMENT '类别id,多个,用/分开',
  `tag_ids` varchar(20) DEFAULT NULL COMMENT '标签id,多个,用,分开',
  `status` int(1) NOT NULL COMMENT '文章状态：0-草稿，1-博文',
  `write_time` datetime DEFAULT NULL COMMENT '撰写时间',
  `pub_time` datetime DEFAULT NULL COMMENT '发布时间',
  `hits` int(10) DEFAULT '0',
  `code` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`) USING BTREE,
  UNIQUE KEY `article_code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------

-- ----------------------------
-- Table structure for `t_authors`
-- ----------------------------
DROP TABLE IF EXISTS `t_authors`;
CREATE TABLE `t_authors` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `account` varchar(20) NOT NULL COMMENT '账户名',
  `password` varchar(32) NOT NULL COMMENT '账户密码',
  `pen_name` varchar(20) DEFAULT NULL COMMENT '笔名',
  `email` varchar(30) DEFAULT NULL COMMENT '常用邮箱',
  `profile` text COMMENT '个人介绍',
  `verification_code` varchar(32) DEFAULT NULL COMMENT '验证码',
  `varif_code_deadline` datetime DEFAULT NULL COMMENT '验证码截止时间',
  `security_question_id` int(10) DEFAULT NULL COMMENT '密保问题id',
  `user_status` int(1) DEFAULT NULL COMMENT '用户状态:0禁用，1启用',
  `other_info` varchar(500) DEFAULT NULL COMMENT '其他信息',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_authors
-- ----------------------------

-- ----------------------------
-- Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `status` tinyint(1) DEFAULT '0' COMMENT '展示状态',
  `code` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_code` (`code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
-- ----------------------------

-- ----------------------------
-- Table structure for `t_friendlink`
-- ----------------------------
DROP TABLE IF EXISTS `t_friendlink`;
CREATE TABLE `t_friendlink` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) NOT NULL,
  `description` varchar(300) DEFAULT NULL,
  `website` varchar(60) NOT NULL,
  `hits` int(10) DEFAULT '0',
  `priority` int(2) DEFAULT NULL,
  `web_author_name` varchar(20) DEFAULT NULL,
  `web_author_email` varchar(30) DEFAULT NULL,
  `add_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_friendlink
-- ----------------------------

-- ----------------------------
-- Table structure for `t_guest`
-- ----------------------------
DROP TABLE IF EXISTS `t_guest`;
CREATE TABLE `t_guest` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `email` varchar(30) DEFAULT NULL COMMENT '通讯邮箱',
  `nickname` varchar(30) NOT NULL COMMENT '昵称',
  `personal_website` varchar(50) DEFAULT NULL COMMENT '个人网址',
  `access_ip` varchar(30) DEFAULT NULL COMMENT '访问IP',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_guest
-- ----------------------------


-- ----------------------------
-- Table structure for `t_message`
-- ----------------------------
DROP TABLE IF EXISTS `t_message`;
CREATE TABLE `t_message` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) DEFAULT NULL COMMENT '父id',
  `content` varchar(600) NOT NULL COMMENT '内容',
  `user_type` int(1) NOT NULL COMMENT '留言作者类别（author作者，guest访客）',
  `author_id` int(10) NOT NULL COMMENT '作者id',
  `article_id` int(10) NOT NULL COMMENT '所属文章id',
  `pub_time` datetime DEFAULT NULL COMMENT '发表时间',
  `block_id` int(10) DEFAULT NULL COMMENT '所在文章的评论区域属于第几块',
  `support_count` int(10) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
-- ----------------------------


-- ----------------------------
-- Table structure for `t_project`
-- ----------------------------
DROP TABLE IF EXISTS `t_project`;
CREATE TABLE `t_project` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `introduction` varchar(1000) DEFAULT NULL,
  `article_url` varchar(200) DEFAULT NULL,
  `down_url` varchar(100) DEFAULT NULL,
  `hits` int(10) DEFAULT NULL,
  `pub_time` datetime DEFAULT NULL,
  `status` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_project
-- ----------------------------

-- ----------------------------
-- Table structure for `t_recommend`
-- ----------------------------
DROP TABLE IF EXISTS `t_recommend`;
CREATE TABLE `t_recommend` (
  `id` bigint(18) NOT NULL AUTO_INCREMENT,
  `title` varchar(150) DEFAULT NULL COMMENT '标题',
  `has_content` int(1) DEFAULT '0' COMMENT '是否包含内容',
  `article_url` varchar(300) DEFAULT NULL COMMENT '文章对应的url',
  `content_id` int(10) DEFAULT NULL COMMENT '对应的内容id，前提为has_content为true，即1',
  `hits` int(10) DEFAULT '0' COMMENT '点击量',
  `pub_time` datetime DEFAULT NULL COMMENT '发布时间',
  `summary` varchar(300) DEFAULT NULL COMMENT '简要说明',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_recommend
-- ----------------------------


-- ----------------------------
-- Table structure for `t_security_question`
-- ----------------------------
DROP TABLE IF EXISTS `t_security_question`;
CREATE TABLE `t_security_question` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_type` int(1) NOT NULL COMMENT '用户类别（管理员，作者）',
  `question01` varchar(30) NOT NULL,
  `answer01` varchar(20) NOT NULL,
  `question02` varchar(30) DEFAULT NULL,
  `answer02` varchar(20) DEFAULT NULL,
  `question03` varchar(30) DEFAULT NULL,
  `answer03` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_security_question
-- ----------------------------

-- ----------------------------
-- Table structure for `t_tag`
-- ----------------------------
DROP TABLE IF EXISTS `t_tag`;
CREATE TABLE `t_tag` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL COMMENT '标签名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
