package com.morben.restapi.security;

import com.morben.restapi.model.UserAccount;
import com.morben.restapi.service.impl.UserAccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class AppUserDetailsService implements UserDetailsService {

    @Autowired
    private UserAccountServiceImpl userAccountServiceImpl;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Optional<UserAccount> userOptional = userAccountServiceImpl.findByLogin(login);
        UserAccount user =  userOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
        if(!user.getActive())
            throw new DisabledException("Usuario desativado");
        return new UserSystem(user, getPermissoes(user));
    }

    private Collection<? extends GrantedAuthority> getPermissoes(UserAccount user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
//        user.getPermission().forEach(p -> authorities.add(new SimpleGrantedAuthority(p.getDescription))); TODO: implementar permissoes
        authorities.add(new SimpleGrantedAuthority("ROLE_TESTE"));
        return authorities;
    }
}
