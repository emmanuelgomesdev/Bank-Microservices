package com.emmanuel.authservice.auth.service;

import com.emmanuel.authservice.auth.application.command.AuthCommandCreate;
import com.emmanuel.authservice.auth.application.command.LoginCommand;
import com.emmanuel.authservice.auth.application.result.AuthResult;
import com.emmanuel.authservice.auth.application.result.LoginResult;
import com.emmanuel.authservice.auth.domain.entity.AuthUser;
import com.emmanuel.authservice.auth.mapper.AuthApplicationMapper;
import com.emmanuel.authservice.auth.repository.AuthUserRepository;
import com.emmanuel.banksecurity.jwt.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService {

    private final AuthUserRepository authUserRepository;
    private final AuthApplicationMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthService(AuthUserRepository authUserRepository,
                       AuthApplicationMapper mapper,
                       PasswordEncoder passwordEncoder,
                       AuthenticationManager authenticationManager,
                       JwtService jwtService) {
        this.authUserRepository = authUserRepository;
        this.mapper = mapper;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Transactional
    public AuthResult register(AuthCommandCreate command){

        if (authUserRepository.existsByEmail(command.email())){
            throw new IllegalArgumentException("Email Already Exists");
        }

        AuthUser authUser = AuthUser.create(
                command.email(),
                passwordEncoder.encode(command.password())
        );

        var saved = authUserRepository.save(authUser);

        return mapper.toResult(saved);
    }

    @Transactional(readOnly = true)
    public LoginResult login(LoginCommand command){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        command.email(),
                        command.password()
                )
        );

        String token = jwtService.generateToken(command.email());

        return new LoginResult(
                token,
                "Bearer"
        );


    }
}
