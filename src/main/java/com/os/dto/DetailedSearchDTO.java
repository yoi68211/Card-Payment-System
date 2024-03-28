package com.os.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**

 * @auther : 김홍성 작성자
 */
@Data
public class DetailedSearchDTO {

    private String startDt;
    private String endDt;
    private String status;
    private int dateRange;
    private String docNumber;
    private String customerName;
    private String email;




}
