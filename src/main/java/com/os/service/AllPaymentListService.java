package com.os.service;

import com.os.dto.AllPaymentListDto;
import com.os.dto.DetailedSearchDTO;
import com.os.entity.Payment;
import com.os.entity.QPayment;
import com.os.repository.PaymentRepository;
import com.os.util.OrderStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
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

@Service
@RequiredArgsConstructor
@Transactional
public class AllPaymentListService {

    private final PaymentRepository paymentRepository;
    private final JPAQueryFactory query;


    public Page<AllPaymentListDto> findAll(Pageable pageable) {
        Page<Payment> allPaymentsPage = paymentRepository.findByPaymentDelYn('n', pageable);
        return allPaymentsPage.map(AllPaymentListDto::toAllPaymentListDto);
    }

    public Page<AllPaymentListDto> findByTitleContaining(String keyword, Pageable pageable) {
        Page<Payment> allPaymentsPage = paymentRepository.findByPaymentTitleContainingAndPaymentDelYnNot(keyword, 'Y', pageable);
        return allPaymentsPage.map(AllPaymentListDto::toAllPaymentListDto);
    }


    /**
     * @auther : 김홍성
     */

    public Page<AllPaymentListDto> detailSearch(DetailedSearchDTO searchDTO, Pageable pageable) {

        String status = searchDTO.getStatus();
        int dateRange = searchDTO.getDateRange();
        String DocNumber = (searchDTO.getDocNumber());
        String name = searchDTO.getCustomerName();
        String startDt = searchDTO.getStartDt();
        String endDt = searchDTO.getEndDt();



        QPayment payment = QPayment.payment;

        List<Payment> resultList  = query
                .select(payment)
                .from(payment)
                .where(
                        eqDocNumber(DocNumber)
                        ,likeCustomerName(name)
                        ,betweenDateRange(dateRange)
                        ,eqStatus(status)
                        ,betweenDt(startDt,endDt)
                )
                .fetch();

        List<AllPaymentListDto> result = convertToAllPaymentListDto(resultList);


         return new PageImpl<>(result,pageable,result.size());
    }


    private List<AllPaymentListDto> convertToAllPaymentListDto(List<Payment> paymentList) {
        return paymentList.stream()
                .map(AllPaymentListDto::toAllPaymentListDto)
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


    private BooleanExpression betweenDateRange(int dateRange) {

        if (dateRange == 1 || dateRange == 3 || dateRange == 6 || dateRange == 12)  {
            LocalDateTime startDate = LocalDateTime.now().minusMonths(dateRange);
            LocalDateTime endDate = LocalDateTime.now();

            return payment.paymentDelYn.eq('N').and(payment.createTime.between(startDate, endDate));
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
            return payment.paymentDelYn.eq('N').and(payment.createTime.between(startDate, endDate));
        }
        return null;
    }



}