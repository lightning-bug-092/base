package com.hius.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hius.exception.CustomizeException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
public class RestTemplateUtil {
    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;
    @Autowired
    private ObjectMapper jsonObjectMapper;

    public <T, R> T post(String url, Map<String, String> header, R request, Class<T> responseType) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        header.forEach((k, v) -> headers.add(k, v));
        HttpEntity<R> requestEntity = new HttpEntity<>(request, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        if (!response.getStatusCode().is2xxSuccessful()) {
            log.error("post() - error code{} - body {}", response.getStatusCode(), response.getBody());
            throw new CustomizeException(String.valueOf(response.getStatusCode().value()),
                    String.format("RestClient return error code: %s", response.getBody()));
        }
        try{
            return jsonObjectMapper.readValue(response.getBody(), responseType);
        }catch (Exception e){
            log.error("RestClient.post() - error"+ ExceptionUtils.getStackTrace(e));
            throw new Exception("return invalid response format");
        }
    }

}
