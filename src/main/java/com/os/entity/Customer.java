package com.os.entity;
import com.os.util.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "customer_id")
    private Long id;            // 고객 IDX

    @Column(nullable = false)
    @NotBlank(message = "이름을 입력 해주세요")
    @Length(min = 1,max= 16 , message = "이름은 최대 16글자 까지입니다.")
    private String customerName;               // 고객 이름

    @Column(nullable = false)
    @Length(min = 1,max = 64,message = "이메일은 최대 64글자입니다")
    @Email
    @NotBlank(message = "이메일을 입력 해주세요")
    private String customerEmail;              // 고객 이메일

    @Column(nullable = false)
    @NotBlank(message = "핸드폰 번호를 입력해주세요")
//    @Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}$", message = "휴대폰 번호 양식에 맞지 않습니다.")
    private String customerPhone;              // 고객 전화번호

    @Column(nullable = false)
    private String customerAddress;            // 고객 주소

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id",nullable = false)
    private User user;                        // 유저 IDX


    @OneToOne(mappedBy= "customer" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Payment payments;


//    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<AutoPayment> autoPayments;


}
