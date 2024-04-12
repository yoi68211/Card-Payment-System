package com.os.dto;

import com.os.entity.AutoPayment;
import com.os.util.AutoStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class AutoPaymentDTOC {
    private Long id;
    private LocalDateTime paymentNextTime;
    private int autoPayCount;
    private AutoStatus autoStatus;
    private LocalDate autoPayDate;

    public static AutoPaymentDTOC autoPaymentInfoDTO(AutoPayment autoPayment){
        AutoPaymentDTOC autoPaymentDTO = AutoPaymentDTOC.builder()
                .id(autoPayment.getId())
                .paymentNextTime(autoPayment.getPaymentNextTime())
                .autoPayCount(autoPayment.getAutoPayCount())
                .autoPayDate(autoPayment.getAutoPayDate())
                .autoStatus(autoPayment.getAutoStatus())
                .build();

        return autoPaymentDTO;
    }
}
