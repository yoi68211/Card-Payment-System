package com.os.entity;

import com.os.util.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
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
    @Email(message = "email형식으로 작성해주세요")
    private String email;

    @Column(nullable = false)
//    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8.20}", message = "비밀번호는 영문 대,소문자와 숫자, 특수기호가 적어도 1개 이상씩 포함된 8 ~20자의 비밀번호여야 합니다.")
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
