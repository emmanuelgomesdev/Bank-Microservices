package com.emmanuel.authservice.auth.domain.entity;

import com.emmanuel.authservice.auth.domain.enums.AuthUserRole;
import com.emmanuel.authservice.auth.domain.enums.AuthUserStatus;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "auth_users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
public class AuthUser {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private AuthUserRole role;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private AuthUserStatus status;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_at",nullable = false)
    private LocalDateTime updatedDate;


    public static AuthUser create(
            String email,
            String password
    ){

        AuthUser authUser = new AuthUser();

        authUser.email = email;
        authUser.password = password;
        authUser.role = AuthUserRole.USER;
        authUser.status = AuthUserStatus.ACTIVE;
        authUser.createdDate = LocalDateTime.now();
        authUser.updatedDate = LocalDateTime.now();

        return authUser;
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {return password;}

    public AuthUserRole getRole() {
        return role;
    }

    public AuthUserStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}
