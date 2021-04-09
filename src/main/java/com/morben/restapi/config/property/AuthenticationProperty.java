package com.morben.restapi.config.property;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties("authentication")
public class AuthenticationProperty {
    private final Security security = new Security();

    @Getter
    @Setter
    public static class Security {
        private String origemPermitida;
        private boolean enableHttps;
    }
}
