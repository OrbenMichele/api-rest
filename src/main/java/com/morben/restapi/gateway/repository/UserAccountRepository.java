package com.morben.restapi.gateway.repository;

import com.morben.restapi.model.UserAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAccountRepository extends JpaRepository<UserAccount, UUID>{

    public Optional<UserAccount> findByLogin(String login);
}
