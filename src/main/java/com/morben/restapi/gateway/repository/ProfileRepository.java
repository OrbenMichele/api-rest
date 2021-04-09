package com.morben.restapi.gateway.repository;

import com.morben.restapi.model.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID>{

}
