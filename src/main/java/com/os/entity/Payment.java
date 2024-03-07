package com.os.entity;

import com.os.util.OrderStatus;
import com.os.util.OrderType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Payment {

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    private String document_no;
    private String title;
    private String bizTo;
    private String memo;
    private OrderType type;
    private OrderStatus status;
    private LocalDateTime paymentDate;
    private LocalDateTime paymentCycle;

    @CreationTimestamp
    private LocalDateTime createTime;
    @UpdateTimestamp
    private LocalDateTime updateTime;

    @OneToMany(mappedBy = "payment", fetch = FetchType.LAZY,cascade =CascadeType.REMOVE)
    private List<Product> products;

    @OneToMany(mappedBy = "payment",fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Customer> customers;

}
