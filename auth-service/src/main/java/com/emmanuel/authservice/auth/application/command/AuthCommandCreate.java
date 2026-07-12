package com.emmanuel.authservice.auth.application.command;

public record AuthCommandCreate(

        String email,
        String password

) {
}
