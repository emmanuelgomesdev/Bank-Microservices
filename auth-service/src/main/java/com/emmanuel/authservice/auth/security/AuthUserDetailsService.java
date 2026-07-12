package com.emmanuel.authservice.auth.security;

import com.emmanuel.authservice.auth.repository.AuthUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthUserDetailsService implements UserDetailsService {

    private final AuthUserRepository authUserRepository;

    public AuthUserDetailsService(AuthUserRepository authUserRepository) {
        this.authUserRepository = authUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        var authUser = authUserRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("Not found" + email));

        return new AuthUserDetails(authUser);
    }
}
