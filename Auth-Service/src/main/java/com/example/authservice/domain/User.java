package com.example.authservice.domain;

import com.example.authservice.factory.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
@Entity
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy =GenerationType.IDENTITY )
    private Long id;
    @Column(name="username", nullable = false)
    private String username;
    @Column(name="password", nullable = false)
    private String password;
    @Column(name="isLocked")
    private Boolean locked= false;
    @Column(name="isEnabled")
    private Boolean enabled= true;
    @Column(name="create_at")
    private LocalDateTime createdAt;
    @Enumerated(EnumType.STRING)
    private Role role;

}
