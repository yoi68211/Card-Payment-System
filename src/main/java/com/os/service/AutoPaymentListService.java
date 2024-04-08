package com.os.service;

import com.os.dto.AllPaymentListDto;
import com.os.dto.AutoDetailedSearchDTO;
import com.os.dto.AutoPaymentListDto;
import com.os.dto.DetailedSearchDTO;
import com.os.entity.Payment;
import com.os.repository.AutoPaymentRepository;
import com.os.repository.PaymentRepository;
import com.os.util.AutoStatus;
import com.os.util.BizTo;
import com.os.util.OrderStatus;
import com.os.util.OrderType;
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

import static com.os.entity.QPayment.payment;
import static com.os.util.OrderType.auto;

@Service
@RequiredArgsConstructor
@Transactional
public class AutoPaymentListService {

    private final PaymentRepository paymentRepository;
    private final EntityManager em;


    public Page<AutoPaymentListDto> findAll(Pageable pageable) {
        Page<Payment> allPaymentsPage = paymentRepository.findByPaymentTypeAndAutoPayments_AutoStatusNotNull(OrderType.auto, pageable);

        return allPaymentsPage.map(AutoPaymentListDto::toAutoPaymentListDto);
    }

    public Page<AutoPaymentListDto> findByNameContaining(String keyword, Pageable pageable) {
        Page<Payment> allPaymentsPage = paymentRepository.findByCustomerCustomerNameContainingAndPaymentTypeAndAutoPayments_AutoStatusNotNull(keyword, OrderType.auto, pageable);

        return allPaymentsPage.map(AutoPaymentListDto::toAutoPaymentListDto);
    }

    public Page<AutoPaymentListDto> detailSearch(AutoDetailedSearchDTO searchDTO, Pageable pageable) {

        String status = searchDTO.getStatus();
        String DocNumber = (searchDTO.getDocNumber());
        String name = searchDTO.getCustomerName();
        String startDt = searchDTO.getStartDt();
        String endDt = searchDTO.getEndDt();
        String phoneNumber = searchDTO.getPhoneNumber();
        String transactionStatus = searchDTO.getTransactionStatus();
        String payType = searchDTO.getPayType();


        JPAQuery<Payment> query = new JPAQuery<>(em);

        query.select(payment)
                .from(payment)
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

        List<Payment> resultList = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();


        List<AutoPaymentListDto> result = convertToAllPaymentListDto(resultList);



        return new PageImpl<>(result,pageable,count);
    }


    private List<AutoPaymentListDto> convertToAllPaymentListDto(List<Payment> paymentList) {
        return paymentList.stream()
                .map(AutoPaymentListDto::toAutoPaymentListDto)
                .collect(Collectors.toList());
    }




    //querydsl where 절 BooleanExpression
    private BooleanExpression eqDocNumber(String DocNumber){
        if (StringUtils.hasText(DocNumber)){
            return payment.paymentDelYn.eq('N').and(payment.autoPayments.id.eq(Long.valueOf(DocNumber)));
        }
        return null;
    }


    private BooleanExpression likeCustomerName(String name) {
        if (StringUtils.hasText(name)) {
            return payment.paymentDelYn.eq('N').and(payment.customer.customerName.like("%" + name + "%"));
        }
        return null;
    }




    private BooleanExpression eqStatus(String status) {
        if (StringUtils.hasText(status)) {
            return payment.paymentDelYn.eq('N').and(payment.autoPayments.autoStatus.eq(AutoStatus.valueOf(status)));
        }
        return null;
    }


    private BooleanExpression betweenDt(String startDt ,String endDt) {
        if (StringUtils.hasText(startDt) && StringUtils.hasText(endDt)) {
            LocalDateTime startDate = LocalDateTime.parse(startDt + "T00:00:00");
            LocalDateTime endDate = LocalDateTime.parse(endDt + "T23:59:59");
            return payment.paymentDelYn.eq('N').and(payment.createTime.between(startDate, endDate));
        }
        return null;
    }


    private BooleanExpression likePhoneNumber(String phoneNumber) {
        if (StringUtils.hasText(phoneNumber)) {
            return payment.paymentDelYn.eq('N').and(payment.customer.customerPhone.like("%" + phoneNumber + "%"));
        }
        return null;
    }


    private BooleanExpression eqTransactionStatus(String transactionStatus) {
        if (StringUtils.hasText(transactionStatus)) {
            return payment.paymentDelYn.eq('N').and(payment.paymentStatus.eq(OrderStatus.valueOf(transactionStatus)));
        }
        return null;
    }

    private BooleanExpression eqPayType(String payType) {
        if (StringUtils.hasText(payType)) {
            return payment.paymentDelYn.eq('N').and(payment.paymentBizTo.eq(BizTo.valueOf(payType)));
        }
        return null;
    }

}