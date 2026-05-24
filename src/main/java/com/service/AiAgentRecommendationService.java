package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.AiAgentRecommendationEntity;
import com.utils.PageUtils;

import java.util.List;
import java.util.Map;

/**
 * AI智能推荐服务接口
 */
public interface AiAgentRecommendationService extends IService<AiAgentRecommendationEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 生成课题推荐
     */
    List<AiAgentRecommendationEntity> generateTopicRecommendations(Long studentId);

    /**
     * 生成导师推荐
     */
    List<AiAgentRecommendationEntity> generateTeacherRecommendations(Long studentId);

    /**
     * 标记推荐为已读
     */
    void markAsRead(Long recommendationId);

    /**
     * 获取用户推荐列表
     */
    PageUtils getUserRecommendations(Long userId, String userType, Map<String, Object> params);
}
