package com.morben.restapi.service.impl;

import com.morben.restapi.exceptions.ErrorMessages;
import com.morben.restapi.exceptions.RestApiServiceException;
import com.morben.restapi.gateway.repository.UserAccountRepository;
import com.morben.restapi.model.UserAccount;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserAccountServiceImpl {

    @Autowired
    private UserAccountRepository userAccountRepository;

    public UserAccount save(UserAccount userAccount) {
        userAccount.setPassword(encodePassword(userAccount.getPassword()));
        userAccount.setActive(true);
        return userAccountRepository.save(userAccount);
    }

    public Optional<UserAccount> findByLogin(String login) {
        return userAccountRepository.findByLogin(login);
    }

    public Optional<UserAccount> findById(UUID id) {
        return userAccountRepository.findById(id);
    }

    public List<UserAccount> findByAll() {
        return userAccountRepository.findAll();
    }

    public void deleteById(UUID id) {
        userAccountRepository.deleteById(id);
    }

    public UserAccount update(UserAccount userAccount, UUID id) {

        try {
            userAccount.setPassword(encodePassword(userAccount.getPassword()));
            UserAccount userAccountSaved = this.findById(id).get();
            if (userAccountSaved == null) {
                throw new RestApiServiceException(ErrorMessages.NO_RECORD_ID_FOUND.getErrorMessage());
            }

            Set<String> ignoreProperties = getIgnoreProperties(userAccount);
            BeanUtils.copyProperties(userAccount, userAccountSaved, ignoreProperties.toArray(new String[0]));
            return userAccountRepository.save(userAccountSaved);
        } catch (Exception e){
            throw new RestApiServiceException(e.getMessage());
        }
    }

    private Set<String> getIgnoreProperties(UserAccount userAccount) {
        Set<String> ignorePropertires = new HashSet<>();
        ignorePropertires.add("id");
        if(userAccount.getPassword() == null || userAccount.getPassword().isEmpty()) {
            ignorePropertires.add("password");
        }
        if(userAccount.getActive() == null) {
            ignorePropertires.add("active");
        }
        return ignorePropertires;
    }

    public void updatePropertyActive(UUID id) {
        UserAccount userAccount = this.findById(id).get();
        userAccount.setActive(!userAccount.getActive());
        userAccountRepository.save(userAccount);
    }

    private String encodePassword(String password) {
        if(password != null) {
            return new BCryptPasswordEncoder().encode(password);
        } else {
            return null;
        }
    }
}
