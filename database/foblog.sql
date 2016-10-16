/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : foblog

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2016-10-16 22:15:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_admin`
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` int(3) NOT NULL AUTO_INCREMENT,
  `admin_account` varchar(30) NOT NULL COMMENT '管理员账户',
  `admin_password` varchar(30) NOT NULL COMMENT '管理员密码',
  `email` varchar(30) NOT NULL COMMENT '邮箱',
  `security_question_id` int(10) DEFAULT NULL COMMENT '密保问题id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------

-- ----------------------------
-- Table structure for `t_article`
-- ----------------------------
DROP TABLE IF EXISTS `t_article`;
CREATE TABLE `t_article` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `title` varchar(70) NOT NULL COMMENT '标题',
  `summary` varchar(600) NOT NULL COMMENT '概要',
  `content` text NOT NULL COMMENT '内容',
  `category_ids` varchar(20) NOT NULL COMMENT '类别id,多个,用/分开',
  `tag_ids` varchar(20) DEFAULT NULL COMMENT '标签id,多个,用/分开',
  `author_id` int(10) NOT NULL COMMENT '作者id',
  `status` int(1) NOT NULL COMMENT '文章状态：0-草稿，1-博文',
  `write_time` datetime DEFAULT NULL COMMENT '撰写时间',
  `pub_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_authors
-- ----------------------------

-- ----------------------------
-- Table structure for `t_category`
-- ----------------------------
DROP TABLE IF EXISTS `t_category`;
CREATE TABLE `t_category` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `parent_id` int(10) DEFAULT NULL COMMENT '父类别id',
  `name` varchar(30) NOT NULL COMMENT '名称',
  `author_id` int(10) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_category
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_message
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
  `author_id` int(10) DEFAULT NULL COMMENT '作者id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
