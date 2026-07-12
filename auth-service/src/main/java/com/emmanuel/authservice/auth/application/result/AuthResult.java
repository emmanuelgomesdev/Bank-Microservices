package com.emmanuel.authservice.auth.application.result;

import com.emmanuel.authservice.auth.domain.enums.AuthUserRole;
import com.emmanuel.authservice.auth.domain.enums.AuthUserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record AuthResult(

        UUID id,
        String email,
        AuthUserRole role,
        AuthUserStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
