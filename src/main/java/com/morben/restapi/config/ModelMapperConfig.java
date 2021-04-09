package com.morben.restapi.config;

import com.morben.restapi.model.Profile;
import com.morben.restapi.model.UserAccount;
import com.morben.restapi.model.request.UserAccountRequestModel;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper.createTypeMap(UserAccountRequestModel.class, UserAccount.class)
                .<UUID>addMapping(UserAccountRequestModel::getProfileId,
                            (dest, value) -> dest.setProfile(new Profile(value)));

        return modelMapper;
    }
}
