package com.emmanuel.authservice.auth.dto.response;

import com.emmanuel.authservice.auth.domain.enums.AuthUserStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record AuthResponse(

        UUID id,
        String email,
        String role,
        AuthUserStatus status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
}
