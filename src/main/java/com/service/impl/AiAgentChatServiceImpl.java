package com.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.dao.AiAgentChatDao;
import com.entity.AiAgentChatEntity;
import com.service.AiAgentChatService;
import com.service.AiService;
import com.utils.PageUtils;
import com.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("aiAgentChatService")
public class AiAgentChatServiceImpl extends ServiceImpl<AiAgentChatDao, AiAgentChatEntity> implements AiAgentChatService {

    @Autowired
    private AiService aiService;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Page<AiAgentChatEntity> page = this.selectPage(
                new Query<AiAgentChatEntity>(params).getPage(),
                new EntityWrapper<AiAgentChatEntity>()
        );
        return new PageUtils(page);
    }

    @Override
    public Map<String, Object> sendMessage(Long userId, String userType, String sessionId, String message) {
        // 如果没有提供sessionId，生成新的
        if (sessionId == null || sessionId.isEmpty()) {
            sessionId = UUID.randomUUID().toString();
        }

        // 保存用户消息
        AiAgentChatEntity userMessage = new AiAgentChatEntity();
        userMessage.setUserId(userId);
        userMessage.setUserType(userType);
        userMessage.setSessionId(sessionId);
        userMessage.setRole("user");
        userMessage.setContent(message);
        userMessage.setMessageType("text");
        userMessage.setCreateTime(new Date());
        this.insert(userMessage);

        // 获取会话历史作为上下文
        List<Map<String, String>> history = getSessionHistoryForContext(sessionId);
        Map<String, Object> context = new HashMap<>();
        context.put("history", history);

        // 调用真实 AI API 获取回复
        String aiResponse = aiService.chat(message, context);

        // 保存AI回复
        AiAgentChatEntity aiMessage = new AiAgentChatEntity();
        aiMessage.setUserId(userId);
        aiMessage.setUserType(userType);
        aiMessage.setSessionId(sessionId);
        aiMessage.setRole("assistant");
        aiMessage.setContent(aiResponse);
        aiMessage.setMessageType("text");
        aiMessage.setCreateTime(new Date());
        this.insert(aiMessage);

        Map<String, Object> result = new HashMap<>();
        result.put("sessionId", sessionId);
        result.put("userMessage", userMessage);
        result.put("aiMessage", aiMessage);
        
        return result;
    }

    @Override
    public PageUtils getSessionHistory(String sessionId, Map<String, Object> params) {
        Page<AiAgentChatEntity> page = this.selectPage(
                new Query<AiAgentChatEntity>(params).getPage(),
                new EntityWrapper<AiAgentChatEntity>()
                        .eq("session_id", sessionId)
                        .orderBy("create_time", true)
        );
        return new PageUtils(page);
    }

    @Override
    public void clearSession(String sessionId) {
        this.delete(new EntityWrapper<AiAgentChatEntity>()
                .eq("session_id", sessionId));
    }

    /**
     * 获取会话历史用于上下文
     */
    private List<Map<String, String>> getSessionHistoryForContext(String sessionId) {
        List<AiAgentChatEntity> history = this.selectList(
            new EntityWrapper<AiAgentChatEntity>()
                .eq("session_id", sessionId)
                .orderBy("create_time", true)
                .last("LIMIT 10") // 最近10条消息
        );
        
        List<Map<String, String>> result = new ArrayList<>();
        for (AiAgentChatEntity msg : history) {
            Map<String, String> msgMap = new HashMap<>();
            msgMap.put("role", msg.getRole());
            msgMap.put("content", msg.getContent());
            result.add(msgMap);
        }
        return result;
    }
}
