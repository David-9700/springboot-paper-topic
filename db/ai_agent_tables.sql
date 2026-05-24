-- AI智能助手对话记录表
CREATE TABLE IF NOT EXISTS `ai_agent_chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `user_type` varchar(50) DEFAULT NULL COMMENT '用户类型(student/teacher/admin)',
  `session_id` varchar(100) DEFAULT NULL COMMENT '会话ID',
  `role` varchar(50) DEFAULT NULL COMMENT '消息角色(user/assistant/system)',
  `content` longtext COMMENT '消息内容',
  `message_type` varchar(50) DEFAULT 'text' COMMENT '消息类型(text/recommendation/analysis/notification)',
  `metadata` longtext COMMENT '元数据(JSON格式)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_session_id` (`session_id`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI智能助手对话记录表';

-- AI智能推荐表
CREATE TABLE IF NOT EXISTS `ai_agent_recommendation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `user_type` varchar(50) DEFAULT NULL COMMENT '用户类型(student/teacher)',
  `recommendation_type` varchar(50) DEFAULT NULL COMMENT '推荐类型(topic/teacher/student/collaboration)',
  `item_id` bigint(20) DEFAULT NULL COMMENT '推荐项目ID',
  `score` double DEFAULT NULL COMMENT '推荐分数(0-1)',
  `reason` varchar(500) DEFAULT NULL COMMENT '推荐理由',
  `extra_data` longtext COMMENT '扩展数据(JSON格式)',
  `is_read` int(11) DEFAULT '0' COMMENT '是否已读(0:未读,1:已读)',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_recommendation_type` (`recommendation_type`),
  KEY `idx_is_read` (`is_read`),
  KEY `idx_create_time` (`create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='AI智能推荐表';

-- 添加索引优化查询性能
ALTER TABLE `xuesheng` ADD INDEX IF NOT EXISTS `idx_yuanxi` (`yuanxi`);
ALTER TABLE `jiaoshi` ADD INDEX IF NOT EXISTS `idx_yuanxi` (`yuanxi`);
ALTER TABLE `ketixinxi` ADD INDEX IF NOT EXISTS `idx_ketifenlei` (`ketifenlei`);
ALTER TABLE `xuantixinxi` ADD INDEX IF NOT EXISTS `idx_zhuangtai` (`zhuangtai`);
ALTER TABLE `kaitibaogao` ADD INDEX IF NOT EXISTS `idx_shenhejieguo` (`shenhejieguo`);
