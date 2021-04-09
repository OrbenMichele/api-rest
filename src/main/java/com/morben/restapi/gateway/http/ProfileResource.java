package com.morben.restapi.gateway.http;

import com.morben.restapi.model.Profile;
import com.morben.restapi.service.impl.ProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/profile")
public class ProfileResource {

    @Autowired
    private ProfileServiceImpl profileServiceImpl;

    @PostMapping
    public ResponseEntity<Profile> insert(@Valid @RequestBody Profile profile) {
        return ResponseEntity.status(HttpStatus.CREATED).body(profileServiceImpl.save(profile));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<Profile> profileFound = profileServiceImpl.findById(id);
        return profileFound.isPresent() ? ResponseEntity.ok(profileFound) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<Profile> findAll() {
        return profileServiceImpl.findByAll();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        profileServiceImpl.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Profile> update(@PathVariable UUID id, @Valid @RequestBody Profile profile) {
        Profile profileSaved = profileServiceImpl.update(profile, id);
        return ResponseEntity.ok(profileSaved);
    }
}
