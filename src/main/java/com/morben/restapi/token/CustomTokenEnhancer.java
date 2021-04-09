package com.morben.restapi.token;

import com.morben.restapi.security.UserSystem;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

public class CustomTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        UserSystem userSystem = (UserSystem) authentication.getPrincipal();

        Map<String, Object> addInfo = new HashMap<>();
        addInfo.put("name", userSystem.getUser().getName());
        addInfo.put("email", userSystem.getUser().getEmail());
        addInfo.put("id", userSystem.getUser().getId().toString());

        ((DefaultOAuth2AccessToken)accessToken).setAdditionalInformation(addInfo);
        return accessToken;
    }
}
