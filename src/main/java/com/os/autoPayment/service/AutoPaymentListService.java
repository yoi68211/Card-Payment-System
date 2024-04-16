package com.os.autoPayment.service;

import com.os.autoPayment.dto.AutoDetailedSearchDTO;
import com.os.autoPayment.dto.AutoPaymentListDTO;
import com.os.autoPayment.entity.AutoPayment;
import com.os.repository.AutoPaymentRepository;
import com.os.util.AutoOrderStatus;
import com.os.util.AutoStatus;
import com.os.util.BizTo;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.os.autoPayment.entity.QAutoPayment.autoPayment;

@Service
@RequiredArgsConstructor
@Transactional
public class AutoPaymentListService {
    private final AutoPaymentRepository autoPaymentRepository;
    private final EntityManager em;

    public Page<AutoPaymentListDTO> findAll(Pageable pageable) {
        Page<AutoPayment> allPaymentsPage = autoPaymentRepository.findAll(pageable);

        return allPaymentsPage.map(AutoPaymentListDTO::toAutoPaymentListDto);
    }

    public Page<AutoPaymentListDTO> findByNameContaining(String keyword, Pageable pageable) {
        Page<AutoPayment> allPaymentsPage = autoPaymentRepository.findByPayment_Customer_CustomerNameAndAutoOrderStatus(keyword, AutoOrderStatus.paid, pageable);

        return allPaymentsPage.map(AutoPaymentListDTO::toAutoPaymentListDto);
    }

    public Page<AutoPaymentListDTO> findByAutoStatus(Pageable pageable) {
        Page<AutoPayment> allPaymentsPage = autoPaymentRepository.findByAutoStatus(AutoStatus.paid, pageable);

        return allPaymentsPage.map(AutoPaymentListDTO::toAutoPaymentListDto);
    }

    public Page<AutoPaymentListDTO> detailSearch(AutoDetailedSearchDTO searchDTO, Pageable pageable) {
        String status = searchDTO.getStatus();                          // autoOrderStatus
        String DocNumber = searchDTO.getDocNumber();
        String name = searchDTO.getCustomerName();
        String startDt = searchDTO.getStartDt();
        String endDt = searchDTO.getEndDt();
        String phoneNumber = searchDTO.getPhoneNumber();
        String transactionStatus = searchDTO.getTransactionStatus();    // autoStatus
        String payType = searchDTO.getPayType();                        // paymentBizTo

        JPAQuery<AutoPayment> query = new JPAQuery<>(em);

        query.select(autoPayment)
                .from(autoPayment)
                .where(
                        eqDocNumber(DocNumber)
                        ,likeCustomerName(name)
                        ,eqStatus(status)
                        ,betweenDt(startDt,endDt)
                        ,likePhoneNumber(phoneNumber)
                        ,eqTransactionStatus(transactionStatus)
                        ,eqPayType(payType)
                )
                .fetch();

        long count = query.stream().count();

        List<AutoPayment> resultList = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<AutoPaymentListDTO> result = convertToAllPaymentListDto(resultList);

        return new PageImpl<>(result,pageable,count);
    }

    private List<AutoPaymentListDTO> convertToAllPaymentListDto(List<AutoPayment> autoPaymentList) {
        return autoPaymentList.stream()
                .map(AutoPaymentListDTO::toAutoPaymentListDto)
                .collect(Collectors.toList());
    }

    //querydsl where 절 BooleanExpression
    private BooleanExpression eqDocNumber(String DocNumber){
        if (StringUtils.hasText(DocNumber)){
            return autoPayment.payment.paymentDelYn.eq('N').and(autoPayment.payment.id.eq(Long.valueOf(DocNumber)));
        }
        return null;
    }

    private BooleanExpression likeCustomerName(String name) {
        if (StringUtils.hasText(name)) {
            return autoPayment.payment.paymentDelYn.eq('N').and(autoPayment.payment.customer.customerName.like("%" + name + "%"));
        }
        return null;
    }

    private BooleanExpression eqStatus(String status) {
        if (StringUtils.hasText(status)) {
            return autoPayment.payment.paymentDelYn.eq('N').and(autoPayment.autoOrderStatus.eq(AutoOrderStatus.valueOf(status)));
        }
        return null;
    }

    private BooleanExpression betweenDt(String startDt ,String endDt) {
        if (StringUtils.hasText(startDt) && StringUtils.hasText(endDt)) {
            LocalDateTime startDate = LocalDateTime.parse(startDt + "T00:00:00");
            LocalDateTime endDate = LocalDateTime.parse(endDt + "T23:59:59");
            return autoPayment.payment.paymentDelYn.eq('N').and(autoPayment.payment.autoPayments.updateTime.between(startDate, endDate));
        }
        return null;
    }

    private BooleanExpression likePhoneNumber(String phoneNumber) {
        if (StringUtils.hasText(phoneNumber)) {
            return autoPayment.payment.paymentDelYn.eq('N').and(autoPayment.payment.customer.customerPhone.like("%" + phoneNumber + "%"));
        }
        return null;
    }


    private BooleanExpression eqTransactionStatus(String transactionStatus) {
        if (StringUtils.hasText(transactionStatus)) {
            return autoPayment.payment.paymentDelYn.eq('N').and(autoPayment.autoStatus.eq(AutoStatus.valueOf(transactionStatus)));
        }
        return null;
    }

    private BooleanExpression eqPayType(String payType) {
        if (StringUtils.hasText(payType)) {
            return autoPayment.payment.paymentDelYn.eq('N').and(autoPayment.payment.paymentBizTo.eq(BizTo.valueOf(payType)));
        }
        return null;
    }
}