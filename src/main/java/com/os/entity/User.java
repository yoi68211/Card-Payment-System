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
public class User{


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false ,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    private List<Memo> memos;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    private List<Customer> customer;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    private List<AutoPayment> autoPayments;

    @OneToOne(mappedBy= "user" , cascade = CascadeType.ALL)
    private  SavePayment savePayment;


    @Builder
    public User(Long id, String username, String email, String password, UserRole role) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }


}
