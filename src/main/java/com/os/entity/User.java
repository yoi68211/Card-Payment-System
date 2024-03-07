package com.os.entity;

import com.os.util.UserRole;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false ,unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany
    @JoinColumn(name = "userId")
    private List<Payment> payments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    private List<Memo> memos;

    @Builder
    public User(Long userId, String username, String email, String password, UserRole role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }


}
