package com.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * AI API 配置类
 */
@Component
@ConfigurationProperties(prefix = "ai")
public class AiApiConfig {
    
    /**
     * DeepSeek 配置
     */
    private Deepseek deepseek = new Deepseek();
    
    /**
     * 文心一言配置
     */
    private Wenxin wenxin = new Wenxin();
    
    /**
     * 当前使用的 AI 提供商 (deepseek/wenxin)
     */
    private String provider = "deepseek";
    
    public static class Deepseek {
        private String apiKey = "";
        private String apiUrl = "https://api.deepseek.com/v1/chat/completions";
        private String model = "deepseek-chat";
        private Double temperature = 0.7;
        private Integer maxTokens = 2000;
        
        // Getters and Setters
        public String getApiKey() { return apiKey; }
        public void setApiKey(String apiKey) { this.apiKey = apiKey; }
        public String getApiUrl() { return apiUrl; }
        public void setApiUrl(String apiUrl) { this.apiUrl = apiUrl; }
        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public Double getTemperature() { return temperature; }
        public void setTemperature(Double temperature) { this.temperature = temperature; }
        public Integer getMaxTokens() { return maxTokens; }
        public void setMaxTokens(Integer maxTokens) { this.maxTokens = maxTokens; }
    }
    
    public static class Wenxin {
        private String apiKey = "";
        private String secretKey = "";
        private String apiUrl = "https://aip.baidubce.com/rpc/2.0/ai_custom/v1/wenxinworkshop/chat/completions";
        private String model = "ernie-bot";
        private Double temperature = 0.8;
        private Integer maxTokens = 2000;
        
        // Getters and Setters
        public String getApiKey() { return apiKey; }
        public void setApiKey(String apiKey) { this.apiKey = apiKey; }
        public String getSecretKey() { return secretKey; }
        public void setSecretKey(String secretKey) { this.secretKey = secretKey; }
        public String getApiUrl() { return apiUrl; }
        public void setApiUrl(String apiUrl) { this.apiUrl = apiUrl; }
        public String getModel() { return model; }
        public void setModel(String model) { this.model = model; }
        public Double getTemperature() { return temperature; }
        public void setTemperature(Double temperature) { this.temperature = temperature; }
        public Integer getMaxTokens() { return maxTokens; }
        public void setMaxTokens(Integer maxTokens) { this.maxTokens = maxTokens; }
    }
    
    // Getters and Setters
    public Deepseek getDeepseek() { return deepseek; }
    public void setDeepseek(Deepseek deepseek) { this.deepseek = deepseek; }
    public Wenxin getWenxin() { return wenxin; }
    public void setWenxin(Wenxin wenxin) { this.wenxin = wenxin; }
    public String getProvider() { return provider; }
    public void setProvider(String provider) { this.provider = provider; }
}
