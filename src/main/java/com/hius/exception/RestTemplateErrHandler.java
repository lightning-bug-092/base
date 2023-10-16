package com.hius.exception;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hius.dao.response.CommonResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Component
public class RestTemplateErrHandler implements ResponseErrorHandler {
    private static final Logger logger = LoggerFactory.getLogger(RestTemplateErrHandler.class);

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        return ((response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR
                || response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR)
                && response.getStatusCode() != HttpStatus.UNAUTHORIZED);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        String body = Optional.ofNullable(getBodyString(response)).orElse("").trim();
        ObjectMapper jsonObjectMapper = new ObjectMapper();
        jsonObjectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        CommonResponse<String> data = jsonObjectMapper.readValue(body, CommonResponse.class);
        if (response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR) {
            throw new CustomizeException(data.getMsg(), data.getData(), HttpStatus.INTERNAL_SERVER_ERROR);
        } else if (response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR) {
            throw new CustomizeException(data.getMsg(), data.getData(),HttpStatus.BAD_REQUEST);
        }
    }
    private String getBodyString(ClientHttpResponse response) {
        try {
            if (response != null && response.getBody() != null) {
                StringBuilder inputStringBuilder = new StringBuilder();
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(response.getBody(), StandardCharsets.UTF_8));
                String line = bufferedReader.readLine();
                while (line != null) {
                    inputStringBuilder.append(line);
                    inputStringBuilder.append('\n');
                    line = bufferedReader.readLine();
                }
                return inputStringBuilder.toString();
            }

        } catch (IOException e) {
            logger.error(ExceptionUtils.getStackTrace(e));
            return null;
        }
        return null;
    }
}
