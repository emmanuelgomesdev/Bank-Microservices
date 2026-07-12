package com.emmanuel.authservice.auth.dto.response;

public record LoginResponse(
        String accessToken,
        String tokenType
) {
}
