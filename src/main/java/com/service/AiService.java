package com.service;

import java.util.Map;

/**
 * AI 服务接口
 */
public interface AiService {
    
    /**
     * 发送消息到 AI 并获取回复
     * @param message 用户消息
     * @param context 上下文历史（可选）
     * @return AI 回复
     */
    String chat(String message, Map<String, Object> context);
    
    /**
     * 生成课题推荐
     * @param studentProfile 学生画像（专业、兴趣等）
     * @return 推荐理由和建议
     */
    String generateTopicRecommendation(Map<String, Object> studentProfile);
    
    /**
     * 生成导师推荐
     * @param studentProfile 学生画像
     * @return 推荐理由和建议
     */
    String generateTeacherRecommendation(Map<String, Object> studentProfile);
}
