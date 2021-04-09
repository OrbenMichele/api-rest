package com.morben.restapi.service;

import com.morben.restapi.model.UserAccount;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserAccountService {

    public UserAccount save(UserAccount userAccount);

    public Optional<UserAccount> findByLogin(String login);

    public Optional<UserAccount> findById(UUID id);

    public List<UserAccount> findByAll();

    public void deleteById(UUID id);

    public UserAccount update(UserAccount userAccount, UUID id);

    public void updatePropertyActive(UUID id);

}
