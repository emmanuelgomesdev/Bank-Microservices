package com.emmanuel.authservice.auth.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AuthRequest(

        @Email(message = "Email invalid")
        @NotBlank(message = "Email is required")
        @Size(max = 150)
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100)
        String password


) {
}
