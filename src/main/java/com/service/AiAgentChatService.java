package com.service;

import com.baomidou.mybatisplus.service.IService;
import com.entity.AiAgentChatEntity;
import com.utils.PageUtils;

import java.util.Map;

/**
 * AI智能助手服务接口
 */
public interface AiAgentChatService extends IService<AiAgentChatEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 发送消息并获取AI回复
     */
    Map<String, Object> sendMessage(Long userId, String userType, String sessionId, String message);

    /**
     * 获取会话历史
     */
    PageUtils getSessionHistory(String sessionId, Map<String, Object> params);

    /**
     * 清除会话
     */
    void clearSession(String sessionId);
}
