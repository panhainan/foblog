/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50631
Source Host           : localhost:3306
Source Database       : foblog

Target Server Type    : MYSQL
Target Server Version : 50631
File Encoding         : 65001

Date: 2016-10-24 21:48:55
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
  `summary` varchar(600) DEFAULT NULL COMMENT '概要',
  `content` text COMMENT '内容',
  `category_ids` varchar(20) DEFAULT NULL COMMENT '类别id,多个,用/分开',
  `tag_ids` varchar(20) DEFAULT NULL COMMENT '标签id,多个,用,分开',
  `author_id` int(10) NOT NULL COMMENT '作者id',
  `status` int(1) NOT NULL COMMENT '文章状态：0-草稿，1-博文',
  `write_time` datetime DEFAULT NULL COMMENT '撰写时间',
  `pub_time` datetime DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_article
-- ----------------------------
INSERT INTO `t_article` VALUES ('1', '博客初建', '修改：本人博客开篇，网站初建，欢迎大家关注，多交流。', '修改：hello，大家好，欢迎来到我的博客网站，希望大家多沟通交流。谢谢大家。0.08469567153005153', '1', '1,2,3,', '1', '1', '2016-10-13 00:35:48', '2016-10-15 00:35:48');
INSERT INTO `t_article` VALUES ('2', '说说Java中的final、finally和finalize', '这是Java程序员面试宝典中一道面试题，同时很多公司企业面试的时候也喜欢问这道题，前些天一位学长(斌哥)回来一起聚聚的时候还说自己面试的时候遇到了这道题。现在我们就来讲讲这道题，加深自己的理解。全都是文字性的东西，讲的可能比较乏味，但是应该挺好懂的，不懂的可以一起交流。', '<h3 id=\"final\">final</h3>\n<p>想必大家都知道这是Java中定义常量的一个关键字吧;不过它可不止这一个用处啊，下面细细讲来。</p>\n<h4 id=\"final-变量v组合，即使用final定义变量v。\">final+变量v组合，即使用final定义变量v。</h4>\n<p>  解释：变量v一旦被初始化，则不能再被修改了。\n  这里的初始化有两个地方，一是定义的时候初始化，二是在构造函数中。二者只能选其一，而且初始化之后便只能读取不能修改了。\n  这里的不能被修改对不同的变量有不同的定义。对于基本类型定义的普通变量v来说，是指v的值不能被改变，即就是常量了。对于是类定义的对象变量v来说，是指v的引用不可变。\n针对这一组合还有一种比较特殊的用法，就是在方法的形式参数中使用。这一用法对于基本类型的变量是没有实际意义的，因为Java中的方法调用在传基本类型的参数时是传值调用的；但是对于对象变量很实用，因为Java中的方法调用在传对象时传的是对象的引用，而如果在调用的这个方法体中的某处修改了该对象的引用可能会影响到其他地方使用该对象变量的引用，所以这里就需要用到&lt;span class=&quot;hljs-keyword&quot;&gt;final&lt;/span&gt;定义形式参数对象变量不可变。</p>\n<h4 id=\"final-方法m组合，即使用final关键字定义方法m。\">final+方法m组合，即使用final关键字定义方法m。</h4>\n<p>  为什么要将方法定义为&lt;span class=&quot;hljs-keyword&quot;&gt;final&lt;/span&gt;？我们来看看产生这个组合的需求是什么。\n需求有以下两点：</p>\n<p>  认为这个方法提供的功能已经满足了自己的要求，不需要并且不允许通过继承等手段来覆盖重写这个方法，这里是允许继承这个方法直接使用的，但是不允许修改了。\n  允许编译器将所有对该方法的调用转换为行内(inline)调用的机制，即是把调用该方法的代码直接转换为该方法的全部代码，而不是通过保存断点、压栈等手段进入到该方法。可以看出这样会使程序免去了方法调用的时间，使得效率提高了一些；但是得注意m的方法体比较小，并且调用该方法的地方不多，只是偶尔使用一下，否则会适得其反。 哈哈，现在应该知道了final+方法的组合的用法了吧。</p>\n<h4 id=\"final-类c组合，即是final关键字定义类。\">final+类c组合，即是final关键字定义类。</h4>\n<p>  与上述讲方法的有点类似的地方，&lt;span class=&quot;hljs-keyword&quot;&gt;final&lt;/span&gt;定义类是表示此类c被认为设计的很完美了，不需要进行修改或者扩展等。所以&lt;span class=&quot;hljs-keyword&quot;&gt;final&lt;/span&gt;定义的类c是不能被继承的，也说明了&lt;span class=&quot;hljs-keyword&quot;&gt;final&lt;/span&gt;关键字不能和&lt;span class=&quot;hljs-keyword&quot;&gt;abstract&lt;/span&gt;关键字一起使用。对于&lt;span class=&quot;hljs-keyword&quot;&gt;final&lt;/span&gt;类c中的成员变量，可以定义为&lt;span class=&quot;hljs-keyword&quot;&gt;final&lt;/span&gt;，可以不定义；而c中的方法则都是&lt;span class=&quot;hljs-keyword&quot;&gt;final&lt;/span&gt;类型的，不管是否加关键字&lt;span class=&quot;hljs-keyword&quot;&gt;final&lt;/span&gt;。</p>\n<h3 id=\"finally\">finally</h3>\n<p>  这个大家应该经常在&lt;span class=&quot;hljs-keyword&quot;&gt;try&lt;/span&gt;语句中看到吧，相信应该都比较懂它的功能。这里解释一下，在&lt;span class=&quot;hljs-keyword&quot;&gt;try&lt;/span&gt; &lt;span class=&quot;hljs-keyword&quot;&gt;catch&lt;/span&gt;语句块中，后面建议加一个&lt;span class=&quot;hljs-keyword&quot;&gt;finally&lt;/span&gt;块，这样被定义在&lt;span class=&quot;hljs-keyword&quot;&gt;finally&lt;/span&gt;块中的代码(用fcode代称一下)，不论&lt;span class=&quot;hljs-keyword&quot;&gt;try&lt;/span&gt;或者&lt;span class=&quot;hljs-keyword&quot;&gt;catch&lt;/span&gt;是否运行，fcode都会在它们(&lt;span class=&quot;hljs-keyword&quot;&gt;try&lt;/span&gt;和&lt;span class=&quot;hljs-keyword&quot;&gt;catch&lt;/span&gt;块)运行之后运行。注意一下这里有个特别的情况，就是如果&lt;span class=&quot;hljs-keyword&quot;&gt;try&lt;/span&gt;或者&lt;span class=&quot;hljs-keyword&quot;&gt;catch&lt;/span&gt;里面有&lt;span class=&quot;hljs-keyword&quot;&gt;return&lt;/span&gt;语句，&lt;span class=&quot;hljs-keyword&quot;&gt;finally&lt;/span&gt;里面的代码块fcode会在&lt;span class=&quot;hljs-keyword&quot;&gt;return&lt;/span&gt;语句执行之前执行。因为一个方法在执行&lt;span class=&quot;hljs-keyword&quot;&gt;return&lt;/span&gt;语句后就会被销毁。 &lt;span class=&quot;hljs-keyword&quot;&gt;finally&lt;/span&gt;它常常用来清理资源，大家应该会在使用jdbc连接数据库的时候用到。</p>\n<h3 id=\"finalize\">finalize</h3>\n<p>  它是方法名，大家或许有可能在看垃圾回收的地方看到过，没错，它确实和垃圾回收有一些关系。\n使用&lt;span class=&quot;hljs-function&quot;&gt;&lt;span class=&quot;hljs-title&quot;&gt;finalize&lt;/span&gt;&lt;span class=&quot;hljs-params&quot;&gt;()&lt;/span&gt;&lt;/span&gt;方法在&lt;span class=&quot;hljs-function&quot;&gt;&lt;span class=&quot;hljs-title&quot;&gt;t&lt;/span&gt;&lt;span class=&quot;hljs-params&quot;&gt;(代称，后面有解释，便于理解)&lt;/span&gt;&lt;/span&gt;时之前做出必要的清理工作。这里的t是指垃圾收集器将对象从内存中清除出去。而什么时候调用呢？是在t1时(即在垃圾回收器确定这个对象没有被引用时，决定删除这个对象之前)对这个对象调用的。在Object类中定义的，所有的类都继承了它。\n看到这里应该都对这三个英文单词有比较深的理解了吧，如果还不理解或者发现文中哪里有什么问题的，欢迎在下面留言，大家一起讨论，帮助更多的人！</p>\n', '5', '2,4,', '1', '1', '2016-10-14 00:09:12', '2016-10-23 21:28:43');
INSERT INTO `t_article` VALUES ('3', '博客初建0.45873475310789313', '本人博客开篇，网站初建，欢迎大家关注，多交流。0.8476197742944783', '<h3 id=\"很快就好了空间和空间和\">很快就好了空间和空间和</h3>\n<p><emoji  class=\"\" data-name=\"smiley\" data-emoji=\"emoji smiley\" align=\"absmiddle\"></emoji></p>\n<p><strong>粗体</strong>\n<em>斜体</em></p>\n<h3 id=\"标题\">标题</h3>\n<p><a href=\"http://panhainan.com\">链接</a>\n表情:<emoji  class=\"fa fa-heart\" data-name=\"fa-heart\" data-emoji=\"emoji fa-heart\" align=\"absmiddle\"></emoji>:</p>\n<ol>\n<li>列表<pre><code class=\"hljs\">代码\n</code></pre><blockquote>\n<p>引用</p>\n</blockquote>\n</li>\n</ol>\n', '1', '1,', '1', '0', '2016-10-14 21:39:38', null);
INSERT INTO `t_article` VALUES ('4', '博客初建0.40716755541429595', '本人博客开篇，网站初建，欢迎大家关注，多交流。0.6076840207267084', 'hello，大家好，欢迎来到我的博客网站，希望大家多沟通交流。谢谢大家。0.12487376657423466', '6', '1,', '1', '0', '2016-10-15 23:01:37', null);
INSERT INTO `t_article` VALUES ('5', '博客初建', '本人博客开篇，网站初建，欢迎大家关注，多交流。', '<h3 id=\"很快就好了空间和空间和\">很快就好了空间和空间和</h3>\n<p><emoji  class=\"\" data-name=\"smiley\" data-emoji=\"emoji smiley\" align=\"absmiddle\"></emoji></p>\n<p><strong>粗体</strong>\n<em>斜体</em></p>\n<h3 id=\"标题\">标题</h3>\n<p><a href=\"http://panhainan.com\">链接</a>\n表情:<emoji  class=\"fa fa-heart\" data-name=\"fa-heart\" data-emoji=\"emoji fa-heart\" align=\"absmiddle\"></emoji>:</p>\n<ol>\n<li>列表<pre><code class=\"hljs\">代码\n</code></pre><blockquote>\n<p>引用</p>\n</blockquote>\n</li>\n</ol>\n', '6', '4,5,1,2,3,', '1', '0', '2016-10-15 23:08:53', '2016-10-23 20:40:10');

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
INSERT INTO `t_authors` VALUES ('1', 'panhainan', '123123', null, 'panhainan@yeah.net', null, null, null, null, '0', null);
INSERT INTO `t_authors` VALUES ('2', 'panhainan12', '123123', null, null, null, null, null, null, '1', null);
INSERT INTO `t_authors` VALUES ('3', 'panhainan32', '123123', null, null, null, null, null, null, '1', null);

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
INSERT INTO `t_category` VALUES ('1', '0', 'Java框架', '1');
INSERT INTO `t_category` VALUES ('5', '0', 'Web开发', '1');
INSERT INTO `t_category` VALUES ('6', '0', 'Web前端', '1');

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
INSERT INTO `t_message` VALUES ('25', '0', 'B对A的文章进行了评论', '0', '1', '1', null, '1');
INSERT INTO `t_message` VALUES ('26', '25', 'C对B的评论进行了评论', '0', '2', '1', null, '1');
INSERT INTO `t_message` VALUES ('27', '25', 'D对B的评论进行了评论', '0', '3', '1', null, '1');
INSERT INTO `t_message` VALUES ('28', '0', 'E对A的文章进行了评论', '0', '4', '1', null, '2');
INSERT INTO `t_message` VALUES ('29', '0', 'F对A的文章进行了评论', '0', '5', '1', null, '3');
INSERT INTO `t_message` VALUES ('30', '25', 'B对C的评论进行了评论', '0', '1', '1', null, '1');
INSERT INTO `t_message` VALUES ('31', '25', 'A对D的评论进行了评论', '1', '1', '1', null, '1');
INSERT INTO `t_message` VALUES ('32', '29', 'Adui F', '1', '1', '1', null, '3');

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_tag
-- ----------------------------
INSERT INTO `t_tag` VALUES ('1', 'WEB', '1');
INSERT INTO `t_tag` VALUES ('2', 'Java', '1');
INSERT INTO `t_tag` VALUES ('3', 'C/C++', '1');
INSERT INTO `t_tag` VALUES ('4', '杂谈', '1');
INSERT INTO `t_tag` VALUES ('5', '博客', '1');
