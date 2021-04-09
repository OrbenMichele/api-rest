package com.morben.restapi.service.impl;

import com.morben.restapi.gateway.repository.ProfileRepository;
import com.morben.restapi.model.Profile;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileServiceImpl {

    @Autowired
    private ProfileRepository profileRepository;

    public Profile save(Profile profile) {
        return profileRepository.save(profile);
    }

    public Optional<Profile> findById(UUID id) {
        return profileRepository.findById(id);
    }

    public List<Profile> findByAll() {
        return profileRepository.findAll();
    }

    public void deleteById(UUID id) {
        profileRepository.deleteById(id);
    }

    public Profile update(Profile pessoa, UUID id) {
        Profile profileSaved = this.findById(id).get();
        BeanUtils.copyProperties(pessoa, profileSaved, "id");
        return profileRepository.save(profileSaved);
    }


}
