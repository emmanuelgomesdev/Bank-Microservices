package com.emmanuel.authservice.auth.mapper;


import com.emmanuel.authservice.auth.application.result.AuthResult;
import com.emmanuel.authservice.auth.application.result.LoginResult;
import com.emmanuel.authservice.auth.domain.entity.AuthUser;
import org.springframework.stereotype.Component;

@Component
public class AuthApplicationMapper {


    public AuthResult toResult(AuthUser authUser){
        return new AuthResult(
                authUser.getId(),
                authUser.getEmail(),
                authUser.getRole(),
                authUser.getStatus(),
                authUser.getCreatedDate(),
                authUser.getUpdatedDate()
        );

    }

    public LoginResult toResult(String accessToken){
        return new LoginResult(
                accessToken,
                "Bearer"
        );
    }

}
