/*
Navicat MySQL Data Transfer

Source Server         : 测试环境
Source Server Version : 50628
Source Host           : 192.168.91.105:3306
Source Database       : dev_cc_system

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2018-08-27 19:30:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for crm_room
-- ----------------------------
DROP TABLE IF EXISTS `crm_room`;
CREATE TABLE `crm_room` (
  `id` varchar(32) NOT NULL COMMENT '房间ID',
  `park_id` varchar(32) DEFAULT '' COMMENT '园区id',
  `name` varchar(32) DEFAULT '' COMMENT '房间名称',
  `code` varchar(32) DEFAULT '' COMMENT '房间编码/别名',
  `full_name` varchar(200) DEFAULT '' COMMENT '房间全称',
  `full_code` varchar(100) DEFAULT '' COMMENT '编码全称',
  `use_status` tinyint(2) DEFAULT '0' COMMENT '房间状态(0.冻结 1.开放 2.已出租 3.已售出)',
  `use_type` varchar(32) DEFAULT '' COMMENT '用途类型',
  `use_by` varchar(200) DEFAULT '' COMMENT '入驻方名称',
  `qrcode_url` varchar(255) DEFAULT '' COMMENT '房间二维码地址',
  `sale_flag` tinyint(2) DEFAULT '1' COMMENT '是否支持销售  0-否 ,1-是',
  `rent_flag` tinyint(2) DEFAULT '1' COMMENT '是否支持租赁  0-否 ,1-是',
  `code_type` varchar(32) DEFAULT '0' COMMENT '编号格式类型',
  `room_type` varchar(32) DEFAULT '0' COMMENT '户型(如: 2.两房一厅 3.三房一厅)',
  `estate_type` varchar(32) DEFAULT '0' COMMENT '地产类型(如: 1.商业 2.办公)',
  `decor_type` varchar(32) DEFAULT '0' COMMENT '装修类型(如: 1.未装修 2.简单装修 3.精装修)',
  `construction_area` decimal(20,2) DEFAULT '0.00' COMMENT '建筑面积',
  `room_area` decimal(20,2) DEFAULT '0.00' COMMENT '套内面积',
  `use_area` decimal(20,2) DEFAULT '0.00' COMMENT '使用面积',
  `sale_price` decimal(20,2) DEFAULT '0.00' COMMENT '市场销售价格',
  `sale_price_total` decimal(20,2) DEFAULT '0.00' COMMENT '市场销售总价',
  `rent_type` varchar(32) DEFAULT '0' COMMENT '租赁类型（1.押一付一 2.押一付二 3.押一付三）',
  `rent_price` decimal(20,2) DEFAULT '0.00' COMMENT '市场租赁报价',
  `manage_type` tinyint(2) DEFAULT '0' COMMENT '物业管理费收费类型（1.按月 2.按面积）',
  `manage_price` decimal(20,2) DEFAULT '0.00' COMMENT '物业管理费',
  `contact_user` varchar(100) DEFAULT '',
  `contact_phone` varchar(32) DEFAULT '',
  `remark` varchar(200) DEFAULT '' COMMENT '说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `status1` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1：正常、-1：删除',
  `status2` varchar(8) DEFAULT '' COMMENT '状态2',
  `ext1` varchar(50) DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(50) DEFAULT '' COMMENT '扩展字段2',
  `ext3` varchar(50) DEFAULT '' COMMENT '扩展字段3',
  `ext4` varchar(50) DEFAULT '' COMMENT '扩展字段4',
  `ext5` varchar(50) DEFAULT '' COMMENT '扩展字段5',
  `ext6` varchar(50) DEFAULT '' COMMENT '扩展字段6',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源实体表';

-- ----------------------------
-- Table structure for crm_room_attachment
-- ----------------------------
DROP TABLE IF EXISTS `crm_room_attachment`;
CREATE TABLE `crm_room_attachment` (
  `id` varchar(32) NOT NULL COMMENT '附件id',
  `file_name` varchar(500) DEFAULT NULL COMMENT '附件名称',
  `file_path` varchar(500) DEFAULT NULL COMMENT '附件路径',
  `file_size` varchar(220) DEFAULT NULL COMMENT '附件大小',
  `file_type` varchar(255) DEFAULT NULL COMMENT '附件类型 doc docx ppt pptx',
  `foreign_id` varchar(220) DEFAULT NULL COMMENT '外键id',
  `module_type` varchar(255) DEFAULT NULL COMMENT '模块类型（如room:房间 contract合同）',
  `module_type_ext` varchar(255) DEFAULT NULL COMMENT '模块附件具体类型（如01 合同扫描件 02 合同附属资料）',
  `order_index` int(11) NOT NULL AUTO_INCREMENT COMMENT '排序值，保证按上传顺序显示',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注/说明',
  `status1` tinyint(2) DEFAULT '1' COMMENT '1：正常、-1：删除',
  `status2` varchar(8) DEFAULT NULL COMMENT '预留状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `ext1` varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_RM_ATTACH_ORDER` (`order_index`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源-图片及附件表';

-- ----------------------------
-- Table structure for crm_room_model_data
-- ----------------------------
DROP TABLE IF EXISTS `crm_room_model_data`;
CREATE TABLE `crm_room_model_data` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '表id',
  `schema_obj_id` varchar(32) DEFAULT '' COMMENT '层级实体id',
  `num1` decimal(20,2) DEFAULT '0.00' COMMENT '数值1',
  `num2` decimal(20,2) DEFAULT '0.00' COMMENT '数值2',
  `num3` decimal(20,2) DEFAULT '0.00' COMMENT '数值3',
  `num4` decimal(20,2) DEFAULT '0.00' COMMENT '数值4',
  `num5` decimal(20,2) DEFAULT '0.00' COMMENT '数值5',
  `num6` decimal(20,2) DEFAULT '0.00' COMMENT '数值6',
  `num7` decimal(20,2) DEFAULT '0.00' COMMENT '数值7',
  `num8` decimal(20,2) DEFAULT '0.00' COMMENT '数值8',
  `num9` decimal(20,2) DEFAULT '0.00' COMMENT '数值9',
  `num10` decimal(20,2) DEFAULT '0.00' COMMENT '数值10',
  `text1` varchar(255) DEFAULT '' COMMENT '文本1',
  `text2` varchar(255) DEFAULT '' COMMENT '文本2',
  `text3` varchar(255) DEFAULT '' COMMENT '文本3',
  `text4` varchar(255) DEFAULT '' COMMENT '文本4',
  `text5` varchar(255) DEFAULT '' COMMENT '文本5',
  `text6` varchar(255) DEFAULT '' COMMENT '文本6',
  `text7` varchar(255) DEFAULT '' COMMENT '文本7',
  `text8` varchar(255) DEFAULT '' COMMENT '文本8',
  `text9` varchar(255) DEFAULT '' COMMENT '文本9',
  `text10` varchar(255) DEFAULT '' COMMENT '文本10',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `status1` varchar(8) DEFAULT '1' COMMENT '1：正常、-1：删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源 - 层级自定义信息表';

-- ----------------------------
-- Table structure for crm_room_relation
-- ----------------------------
DROP TABLE IF EXISTS `crm_room_relation`;
CREATE TABLE `crm_room_relation` (
  `id` varchar(32) NOT NULL DEFAULT '' COMMENT '表id',
  `from_id` varchar(40) DEFAULT NULL COMMENT '关联id1',
  `to_id` varchar(40) DEFAULT NULL COMMENT '关联id2',
  `relation_type` varchar(32) DEFAULT NULL COMMENT '关系类型,RSO房间-层级',
  `relation_type_ext` varchar(32) DEFAULT NULL COMMENT '关系类型预留字段',
  `order_index` int(5) DEFAULT '0' COMMENT '排序值',
  `remark` varchar(200) DEFAULT NULL COMMENT '说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `status1` tinyint(2) NOT NULL DEFAULT '1' COMMENT '1：正常、-1：删除',
  `status2` varchar(8) DEFAULT NULL COMMENT '状态2',
  `ext1` varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  `ext3` varchar(50) DEFAULT NULL COMMENT '扩展字段3',
  `ext4` varchar(50) DEFAULT NULL COMMENT '扩展字段4',
  `ext5` varchar(50) DEFAULT NULL COMMENT '扩展字段5',
  `ext6` varchar(50) DEFAULT NULL COMMENT '扩展字段6',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源关联表';

-- ----------------------------
-- Table structure for crm_room_schema_obj
-- ----------------------------
DROP TABLE IF EXISTS `crm_room_schema_obj`;
CREATE TABLE `crm_room_schema_obj` (
  `id` varchar(32) NOT NULL COMMENT 'ID',
  `parent_id` varchar(32) DEFAULT NULL COMMENT '房间ID',
  `park_id` varchar(32) DEFAULT NULL COMMENT '园区id',
  `schema_id` varchar(255) DEFAULT NULL COMMENT '层级定义id（引用逻辑树层级id）',
  `model_obj_id` varchar(32) DEFAULT NULL,
  `code` varchar(255) DEFAULT NULL,
  `full_code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `view_url` varchar(255) DEFAULT '',
  `order_index` int(11) DEFAULT '10' COMMENT '排序值',
  `remark` varchar(200) DEFAULT NULL COMMENT '说明',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_by` varchar(32) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `update_by` varchar(32) DEFAULT NULL COMMENT '最后修改人',
  `status1` varchar(8) DEFAULT '1' COMMENT '1：正常、-1：删除',
  `status2` varchar(8) DEFAULT NULL COMMENT '状态2',
  `ext1` varchar(50) DEFAULT NULL COMMENT '扩展字段1',
  `ext2` varchar(50) DEFAULT NULL COMMENT '扩展字段2',
  `ext3` varchar(50) DEFAULT NULL COMMENT '扩展字段3',
  `ext4` varchar(50) DEFAULT NULL COMMENT '扩展字段4',
  `ext5` varchar(50) DEFAULT NULL COMMENT '扩展字段5',
  `ext6` varchar(50) DEFAULT NULL COMMENT '扩展字段6',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='房源-层级实体表';
