package com.os.payment.service;

import com.os.payment.entity.Payment;
import com.os.payment.dto.AllPaymentListDTO;
import com.os.payment.dto.DetailedSearchDTO;
import com.os.repository.PaymentRepository;
import com.os.util.OrderStatus;
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

import static com.os.payment.entity.QPayment.payment;

@Service
@RequiredArgsConstructor
@Transactional
public class AllPaymentListService {
    private final PaymentRepository paymentRepository;
    private final EntityManager em;

    public Page<AllPaymentListDTO> findAll(Pageable pageable) {
        Page<Payment> allPaymentsPage = paymentRepository.findByPaymentDelYn('n', pageable);

        return allPaymentsPage.map(AllPaymentListDTO::toAllPaymentListDto);
    }

    public Page<AllPaymentListDTO> findByTitleContaining(String keyword, Pageable pageable) {
        Page<Payment> allPaymentsPage = paymentRepository.findByPaymentTitleContainingAndPaymentDelYnNot(keyword, 'Y', pageable);

        return allPaymentsPage.map(AllPaymentListDTO::toAllPaymentListDto);
    }

    public void updatePaymentDelYnById(Long id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 아이디가 없습니다 id : " + id));
        payment.setPaymentDelYn('Y');

        paymentRepository.save(payment);
    }

    /***
     * @author : 김홍성
     */
    public Page<AllPaymentListDTO> detailSearch(DetailedSearchDTO searchDTO, Pageable pageable) {
        String status = searchDTO.getStatus();
        String DocNumber = (searchDTO.getDocNumber());
        String name = searchDTO.getCustomerName();
        String startDt = searchDTO.getStartDt();
        String endDt = searchDTO.getEndDt();

        JPAQuery<Payment> query = new JPAQuery<>(em);

        query.select(payment)
                .from(payment)
                .where(
                        eqDocNumber(DocNumber)
                        ,likeCustomerName(name)
                        ,eqStatus(status)
                        ,betweenDt(startDt,endDt)
                )
                .fetch();

        long count = query.stream().count();

        List<Payment> resultList = query.offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        List<AllPaymentListDTO> result = convertToAllPaymentListDto(resultList);

        return new PageImpl<>(result,pageable,count);
    }

    private List<AllPaymentListDTO> convertToAllPaymentListDto(List<Payment> paymentList) {
        return paymentList.stream()
                .map(AllPaymentListDTO::toAllPaymentListDto)
                .collect(Collectors.toList());
    }

    //querydsl where 절 BooleanExpression
    private BooleanExpression eqDocNumber(String DocNumber){
        if (StringUtils.hasText(DocNumber)){
            return payment.paymentDelYn.eq('N').and(payment.id.eq(Long.valueOf(DocNumber)));
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
            return payment.paymentDelYn.eq('N').and(payment.paymentStatus.eq(OrderStatus.valueOf(status)));
        }
        return null;
    }

    private BooleanExpression betweenDt(String startDt ,String endDt) {
        if (StringUtils.hasText(startDt) && StringUtils.hasText(endDt)) {
            LocalDateTime startDate = LocalDateTime.parse(startDt + "T00:00:00");
            LocalDateTime endDate = LocalDateTime.parse(endDt + "T23:59:59");
            return payment.paymentDelYn.eq('N').and(payment.updateTime.between(startDate, endDate));
        }
        return null;
    }
}