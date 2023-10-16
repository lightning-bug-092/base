package com.hius.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties
@ConfigurationProperties()
@Data
public class ApplicationConfig {
    private ClientConfig clientConfig;
    @Data
    public static class ClientConfig{
        private long connectionTimeout;
        private long readTimeout;
    }
}
