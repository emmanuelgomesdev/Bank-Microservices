package com.emmanuel.authservice.auth.mapper;

import com.emmanuel.authservice.auth.application.command.AuthCommandCreate;
import com.emmanuel.authservice.auth.application.command.LoginCommand;
import com.emmanuel.authservice.auth.application.result.AuthResult;
import com.emmanuel.authservice.auth.application.result.LoginResult;
import com.emmanuel.authservice.auth.dto.request.AuthRequest;
import com.emmanuel.authservice.auth.dto.request.LoginRequest;
import com.emmanuel.authservice.auth.dto.response.AuthResponse;
import com.emmanuel.authservice.auth.dto.response.LoginResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthRestMapper {

    //REGISTER
    AuthCommandCreate toCommand(AuthRequest request);
    AuthResponse toResponse(AuthResult result);

    //LOGIN
    LoginCommand toCommand(LoginRequest request);
    LoginResponse toResponse(LoginResult result);


}
