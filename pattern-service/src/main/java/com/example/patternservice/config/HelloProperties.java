package com.example.patternservice.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "pattern.hello")
@Data
public class HelloProperties {
    /**
     * Template for greeting message should have 2 %s placeholders: for name and hostname
     */
    private String template = "Pattern Service Hello %s from %s";
}
