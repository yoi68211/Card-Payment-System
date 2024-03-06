package com.os.user;

import com.os.payment.util.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = true)
    private String username;
    @Column(nullable = true,unique = true)
    private String email;
    @Column(nullable = true)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;


    @Builder
    public User(Long userId, String username, String email, String password, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
