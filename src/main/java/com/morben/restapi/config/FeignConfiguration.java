package com.morben.restapi.config;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfiguration implements RequestInterceptor {

    @Value("${potter.authentication.token}")
    private String token;

    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("apiKey", token);
    }
}