package com.hius.config;

import com.hius.exception.RestTemplateErrHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class RestTemplateConfig {
    @Autowired
    private ApplicationConfig appConfig;

    @Bean(name = "restTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder()
                .setConnectTimeout(Duration.ofMillis(appConfig.getClientConfig().getConnectionTimeout()))
                .setReadTimeout(Duration.ofMillis(appConfig.getClientConfig().getReadTimeout()))
                .errorHandler(new RestTemplateErrHandler())
                .build();
    }
}
