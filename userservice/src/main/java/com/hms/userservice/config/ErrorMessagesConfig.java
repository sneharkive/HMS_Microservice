package com.hms.userservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
@ConfigurationProperties(prefix = "errors")
public class ErrorMessagesConfig {
    private Map<String, String> messages;

    public Map<String, String> getMessages() {
        return messages;
    }

    public void setMessages(Map<String, String> messages) {
        this.messages = messages;
    }

    public String getMessage(String key) {
        return messages.getOrDefault(key, "Unknown error");
    }
}
