package com.swu.perfuinder.external.gemini.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "google.gemini")
@Getter
@Setter
public class GeminiConfig {
    private String apiKey;
}