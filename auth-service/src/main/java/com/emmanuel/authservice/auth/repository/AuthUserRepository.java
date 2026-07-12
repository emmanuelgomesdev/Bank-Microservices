package com.emmanuel.authservice.auth.repository;

import com.emmanuel.authservice.auth.domain.entity.AuthUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthUserRepository extends JpaRepository<AuthUser, UUID> {

    Optional<AuthUser> findByEmail(String email);
    boolean existsByEmail(String email);
}
