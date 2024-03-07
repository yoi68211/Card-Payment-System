package com.os.payment;

import com.os.payment.util.OrderStatus;
import com.os.payment.util.OrderType;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.time.LocalDateTime;

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

}
