package com.controller;

import com.service.AiAgentRecommendationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * AI智能推荐控制器测试
 */
@WebMvcTest(AiAgentRecommendationController.class)
class AiAgentRecommendationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AiAgentRecommendationService recommendationService;

    @Test
    void testGenerateTopicRecommendations() throws Exception {
        when(recommendationService.generateTopicRecommendations(anyLong()))
            .thenReturn(new ArrayList<>());

        mockMvc.perform(post("/aiagent/recommendation/generate/topics")
                .param("studentId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    void testGenerateTeacherRecommendations() throws Exception {
        when(recommendationService.generateTeacherRecommendations(anyLong()))
            .thenReturn(new ArrayList<>());

        mockMvc.perform(post("/aiagent/recommendation/generate/teachers")
                .param("studentId", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }

    @Test
    void testGetUserRecommendations() throws Exception {
        // Create a simple HashMap instead of using PageUtils constructor
        java.util.HashMap<String, Object> mockPage = new java.util.HashMap<>();
        mockPage.put("list", new ArrayList<>());
        mockPage.put("total", 0);
        
        when(recommendationService.getUserRecommendations(anyLong(), anyString(), anyMap()))
            .thenReturn(mockPage);

        mockMvc.perform(get("/aiagent/recommendation/list")
                .param("userId", "1")
                .param("userType", "student")
                .param("page", "1")
                .param("limit", "10"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value(0));
    }
}
