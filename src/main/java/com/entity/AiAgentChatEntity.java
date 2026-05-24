package com.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * AI智能助手对话记录表
 */
@Data
@TableName("ai_agent_chat")
public class AiAgentChatEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户类型 (student/teacher/admin)
     */
    private String userType;

    /**
     * 会话ID
     */
    private String sessionId;

    /**
     * 消息角色 (user/assistant/system)
     */
    private String role;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息类型 (text/recommendation/analysis/notification)
     */
    private String messageType;

    /**
     * 元数据(JSON格式，存储额外信息)
     */
    private String metadata;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
