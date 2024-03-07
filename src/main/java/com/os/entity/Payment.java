package com.os.entity;

import com.os.util.BizTo;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Payment {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @Column(nullable = false)
    private String documentNo;
    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BizTo bizTo;

    private String memo;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderType type;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;

    @Column(nullable = false)
    private LocalDateTime paymentDate;
    @Column(nullable = false)
    private LocalDateTime paymentCycle;


    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createTime;
    @UpdateTimestamp
    @Column(nullable = false)
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    private List<Product> products;

    @OneToMany(mappedBy = "payment",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Customer> customers;


//    @Builder
//    public Payment(Long paymentId, String documentNo, String title, BizTo bizTo, String memo, OrderType type, OrderStatus status, LocalDateTime paymentDate, LocalDateTime paymentCycle, LocalDateTime createTime, LocalDateTime updateTime, List<Product> products, List<Customer> customers) {
//        this.paymentId = paymentId;
//        this.documentNo = documentNo;
//        this.title = title;
//        this.bizTo = bizTo;
//        this.memo = memo;
//        this.type = type;
//        this.status = status;
//        this.paymentDate = paymentDate;
//        this.paymentCycle = paymentCycle;
//        this.createTime = createTime;
//        this.updateTime = updateTime;
//        this.products = products;
//        this.customers = customers;
//    }
}
