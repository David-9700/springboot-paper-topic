package com.service.impl;

import com.config.AiApiConfig;
import com.google.gson.*;
import com.service.AiService;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * AI 服务实现类 - 支持 DeepSeek 和文心一言
 */
@Service("aiService")
public class AiServiceImpl implements AiService {
    
    @Autowired
    private AiApiConfig aiConfig;
    
    private final OkHttpClient httpClient;
    private final Gson gson;
    
    public AiServiceImpl() {
        this.httpClient = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }
    
    @Override
    public String chat(String message, Map<String, Object> context) {
        String provider = aiConfig.getProvider();
        
        if ("wenxin".equalsIgnoreCase(provider)) {
            return chatWithWenxin(message, context);
        } else {
            return chatWithDeepSeek(message, context);
        }
    }
    
    @Override
    public String generateTopicRecommendation(Map<String, Object> studentProfile) {
        String prompt = buildTopicRecommendationPrompt(studentProfile);
        return chat(prompt, null);
    }
    
    @Override
    public String generateTeacherRecommendation(Map<String, Object> studentProfile) {
        String prompt = buildTeacherRecommendationPrompt(studentProfile);
        return chat(prompt, null);
    }
    
    /**
     * DeepSeek API 调用
     */
    private String chatWithDeepSeek(String message, Map<String, Object> context) {
        try {
            AiApiConfig.Deepseek config = aiConfig.getDeepseek();
            
            // 构建请求体
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("model", config.getModel());
            requestBody.addProperty("temperature", config.getTemperature());
            requestBody.addProperty("max_tokens", config.getMaxTokens());
            
            // 构建消息数组
            JsonArray messages = new JsonArray();
            
            // 添加系统提示
            JsonObject systemMessage = new JsonObject();
            systemMessage.addProperty("role", "system");
            systemMessage.addProperty("content", "你是论文选题智能助手，专门帮助学生进行论文选题、导师选择、开题报告写作等。请用专业、友好的语气回答。");
            messages.add(systemMessage);
            
            // 添加上下文历史
            if (context != null && context.containsKey("history")) {
                @SuppressWarnings("unchecked")
                List<Map<String, String>> history = (List<Map<String, String>>) context.get("history");
                for (Map<String, String> msg : history) {
                    JsonObject msgObj = new JsonObject();
                    msgObj.addProperty("role", msg.get("role"));
                    msgObj.addProperty("content", msg.get("content"));
                    messages.add(msgObj);
                }
            }
            
            // 添加当前消息
            JsonObject userMessage = new JsonObject();
            userMessage.addProperty("role", "user");
            userMessage.addProperty("content", message);
            messages.add(userMessage);
            
            requestBody.add("messages", messages);
            
            // 发送请求
            RequestBody body = RequestBody.create(
                    gson.toJson(requestBody),
                    MediaType.parse("application/json; charset=utf-8")
            );
            
            Request request = new Request.Builder()
                    .url(config.getApiUrl())
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer " + config.getApiKey())
                    .build();
            
            try (Response response = httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("DeepSeek API 请求失败: " + response.code());
                }
                
                String responseBody = response.body().string();
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
                
                // 解析响应
                JsonObject choice = jsonResponse.getAsJsonArray("choices").get(0).getAsJsonObject();
                JsonObject messageObj = choice.getAsJsonObject("message");
                
                return messageObj.get("content").getAsString();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return "抱歉，AI 服务暂时不可用，请稍后重试。错误信息: " + e.getMessage();
        }
    }
    
    /**
     * 文心一言 API 调用
     */
    private String chatWithWenxin(String message, Map<String, Object> context) {
        try {
            AiApiConfig.Wenxin config = aiConfig.getWenxin();
            
            // 获取 Access Token
            String accessToken = getWenxinAccessToken();
            
            // 构建请求体
            JsonObject requestBody = new JsonObject();
            requestBody.addProperty("temperature", config.getTemperature());
            requestBody.addProperty("max_output_tokens", config.getMaxTokens());
            
            // 构建消息数组
            JsonArray messages = new JsonArray();
            
            // 添加系统提示
            JsonObject systemMessage = new JsonObject();
            systemMessage.addProperty("role", "system");
            systemMessage.addProperty("content", "你是论文选题智能助手，专门帮助学生进行论文选题、导师选择、开题报告写作等。请用专业、友好的语气回答。");
            messages.add(systemMessage);
            
            // 添加上下文历史
            if (context != null && context.containsKey("history")) {
                @SuppressWarnings("unchecked")
                List<Map<String, String>> history = (List<Map<String, String>>) context.get("history");
                for (Map<String, String> msg : history) {
                    JsonObject msgObj = new JsonObject();
                    msgObj.addProperty("role", msg.get("role"));
                    msgObj.addProperty("content", msg.get("content"));
                    messages.add(msgObj);
                }
            }
            
            // 添加当前消息
            JsonObject userMessage = new JsonObject();
            userMessage.addProperty("role", "user");
            userMessage.addProperty("content", message);
            messages.add(userMessage);
            
            requestBody.add("messages", messages);
            
            // 发送请求
            String url = config.getApiUrl() + "?access_token=" + accessToken;
            RequestBody body = RequestBody.create(
                    gson.toJson(requestBody),
                    MediaType.parse("application/json; charset=utf-8")
            );
            
            Request request = new Request.Builder()
                    .url(url)
                    .post(body)
                    .addHeader("Content-Type", "application/json")
                    .build();
            
            try (Response response = httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("文心一言 API 请求失败: " + response.code());
                }
                
                String responseBody = response.body().string();
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
                
                // 解析响应
                return jsonResponse.get("result").getAsString();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            return "抱歉，AI 服务暂时不可用，请稍后重试。错误信息: " + e.getMessage();
        }
    }
    
    /**
     * 获取文心一言 Access Token
     */
    private String getWenxinAccessToken() {
        try {
            AiApiConfig.Wenxin config = aiConfig.getWenxin();
            
            // 文心一言 OAuth 2.0 获取 Token
            String tokenUrl = "https://aip.baidubce.com/oauth/2.0/token";
            
            HttpUrl.Builder urlBuilder = HttpUrl.parse(tokenUrl).newBuilder();
            urlBuilder.addQueryParameter("grant_type", "client_credentials");
            urlBuilder.addQueryParameter("client_id", config.getApiKey());
            urlBuilder.addQueryParameter("client_secret", config.getSecretKey());
            
            Request request = new Request.Builder()
                    .url(urlBuilder.build())
                    .post(RequestBody.create("", null))
                    .build();
            
            try (Response response = httpClient.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new RuntimeException("获取 Access Token 失败: " + response.code());
                }
                
                String responseBody = response.body().string();
                JsonObject jsonResponse = JsonParser.parseString(responseBody).getAsJsonObject();
                
                return jsonResponse.get("access_token").getAsString();
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("获取文心一言 Access Token 失败", e);
        }
    }
    
    /**
     * 构建课题推荐提示词
     */
    private String buildTopicRecommendationPrompt(Map<String, Object> studentProfile) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("请根据以下学生信息，推荐5个合适的论文课题，并说明推荐理由：\n\n");
        
        if (studentProfile.containsKey("major")) {
            prompt.append("专业: ").append(studentProfile.get("major")).append("\n");
        }
        if (studentProfile.containsKey("interests")) {
            prompt.append("兴趣方向: ").append(studentProfile.get("interests")).append("\n");
        }
        if (studentProfile.containsKey("grade")) {
            prompt.append("年级: ").append(studentProfile.get("grade")).append("\n");
        }
        
        prompt.append("\n要求：\n");
        prompt.append("1. 课题要具有研究价值和可行性\n");
        prompt.append("2. 符合学生的专业背景\n");
        prompt.append("3. 每个课题给出简短的推荐理由\n");
        prompt.append("4. 按照推荐程度排序\n");
        
        return prompt.toString();
    }
    
    /**
     * 构建导师推荐提示词
     */
    private String buildTeacherRecommendationPrompt(Map<String, Object> studentProfile) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("请根据以下学生信息，推荐3位合适的指导教师，并说明推荐理由：\n\n");
        
        if (studentProfile.containsKey("major")) {
            prompt.append("专业: ").append(studentProfile.get("major")).append("\n");
        }
        if (studentProfile.containsKey("researchInterest")) {
            prompt.append("研究方向: ").append(studentProfile.get("researchInterest")).append("\n");
        }
        if (studentProfile.containsKey("topic")) {
            prompt.append("意向课题: ").append(studentProfile.get("topic")).append("\n");
        }
        
        prompt.append("\n要求：\n");
        prompt.append("1. 导师的研究方向与学生匹配\n");
        prompt.append("2. 说明每位导师的优势和特点\n");
        prompt.append("3. 给出选择建议\n");
        
        return prompt.toString();
    }
}
