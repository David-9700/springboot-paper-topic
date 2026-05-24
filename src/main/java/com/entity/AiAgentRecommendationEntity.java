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
 * AI智能推荐表
 */
@Data
@TableName("ai_agent_recommendation")
public class AiAgentRecommendationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户类型 (student/teacher)
     */
    private String userType;

    /**
     * 推荐类型 (topic/teacher/student/collaboration)
     */
    private String recommendationType;

    /**
     * 推荐项目ID
     */
    private Long itemId;

    /**
     * 推荐分数 (0-1)
     */
    private Double score;

    /**
     * 推荐理由
     */
    private String reason;

    /**
     * 扩展数据(JSON格式)
     */
    private String extraData;

    /**
     * 是否已读
     */
    private Integer isRead;

    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
}
