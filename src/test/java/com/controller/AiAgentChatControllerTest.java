package com.controller;

import com.entity.AiAgentChatEntity;
import com.service.AiAgentChatService;
import com.utils.R;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * AI智能助手控制器测试
 */
@WebMvcTest(AiAgentChatController.class)
class AiAgentChatControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AiAgentChatService aiAgentChatService;

    private Map<String, Object> testMessage;

    @BeforeEach
    void setUp() {
        testMessage = new HashMap<>();
        testMessage.put("userId", 1L);
        testMessage.put("userType", "student");
        testMessage.put("message", "如何选择合适的课题？");
    }

    @Test
    void testSendMessage() throws Exception {
        Map<String, Object> mockResponse = new HashMap<>();
        mockResponse.put("sessionId", "test-session-123");
        mockResponse.put("userMessage", new AiAgentChatEntity());
        mockResponse.put("aiMessage", new AiAgentChatEntity());

        when(aiAgentChatService.sendMessage(anyLong(), anyString(), anyString(), anyString()))
            .thenReturn(mockResponse);

        mockMvc.perform(post("/aiagent/chat/send")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"userId\":1,\"userType\":\"student\",\"message\":\"如何选择合适的课题？\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    void testGetSessionHistory() throws Exception {
        when(aiAgentChatService.getSessionHistory(anyString(), anyMap()))
            .thenReturn(new com.utils.PageUtils(null));

        mockMvc.perform(get("/aiagent/chat/history")
                .param("sessionId", "test-session-123")
                .param("page", "1")
                .param("limit", "20"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    void testClearSession() throws Exception {
        mockMvc.perform(delete("/aiagent/chat/session/test-session-123"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }
}
