package com.os.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutoPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autoPaymentId;

}
