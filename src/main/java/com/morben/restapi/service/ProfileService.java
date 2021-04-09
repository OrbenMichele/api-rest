package com.morben.restapi.service;

import com.morben.restapi.model.Profile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProfileService {

    public Profile save(Profile profile);

    public Optional<Profile> findById(UUID id);

    public List<Profile> findByAll();

    public void deleteById(UUID id);

    public Profile update(Profile pessoa, UUID id);

}
