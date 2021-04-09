package com.morben.restapi.gateway.http;

import com.morben.restapi.gateway.json.user.UserFactory;
import com.morben.restapi.model.UserAccount;
import com.morben.restapi.model.request.UserAccountRequestModel;
import com.morben.restapi.model.response.UserAccountResponseModel;
import com.morben.restapi.service.impl.UserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserAccountResource {

    @Autowired
    private UserAccountServiceImpl userAccountServiceImpl;

    @Autowired
    private UserFactory userFactory;

    @PostMapping
    public ResponseEntity<UserAccount> insert(@Valid @RequestBody UserAccountRequestModel userAccount) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userAccountServiceImpl.save(userFactory.create(userAccount)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        Optional<UserAccount> userAccountFound = userAccountServiceImpl.findById(id);
        return userAccountFound.isPresent() ? ResponseEntity.ok(userFactory.create(userAccountFound.get())) : ResponseEntity.notFound().build();
    }

    @GetMapping
    public List<UserAccountResponseModel> findAll() {
        return userFactory.create(userAccountServiceImpl.findByAll());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable UUID id) {
        userAccountServiceImpl.deleteById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserAccountResponseModel> update(@PathVariable UUID id, @Valid @RequestBody UserAccountRequestModel userAccount) {
        UserAccount userSaved = userAccountServiceImpl.update(userFactory.create(userAccount), id);
        return ResponseEntity.ok(userFactory.create(userSaved));
    }

    @PutMapping("/{id}/active")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateActive(@PathVariable UUID id) {
        userAccountServiceImpl.updatePropertyActive(id);
    }
}
