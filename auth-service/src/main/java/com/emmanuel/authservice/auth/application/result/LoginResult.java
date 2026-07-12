package com.emmanuel.authservice.auth.application.result;

public record LoginResult(
        String accessToken,
        String tokenType
) {
}
