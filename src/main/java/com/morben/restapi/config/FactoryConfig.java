package com.morben.restapi.config;

import com.morben.restapi.gateway.json.user.UserFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FactoryConfig {
    @Bean
    public UserFactory userFactory() {
        return new UserFactory();
    }
}
