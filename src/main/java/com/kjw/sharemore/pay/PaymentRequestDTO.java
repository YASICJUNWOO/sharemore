package com.kjw.sharemore.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequestDTO {

    private int price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Long couponId;
    private String paymentType;
    
}
