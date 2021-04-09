package com.morben.restapi.gateway.json.user;

import com.morben.restapi.model.UserAccount;
import com.morben.restapi.model.request.UserAccountRequestModel;
import com.morben.restapi.model.response.UserAccountResponseModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class UserFactory {

    @Autowired
    private ModelMapper modelMapper;

    public UserAccountResponseModel create(UserAccount userAccount) {
        return  modelMapper.map(userAccount, UserAccountResponseModel.class);
    }

    public UserAccount create(UserAccountRequestModel userAccountRequestModel) {
        return modelMapper.map(userAccountRequestModel, UserAccount.class);
    }

    public List<UserAccountResponseModel> create(List<UserAccount> userAccounts) {
        return userAccounts
                .stream()
                .map(user -> modelMapper.map(user, UserAccountResponseModel.class))
                .collect(Collectors.toList());
    }
}
