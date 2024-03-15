package com.kjw.sharemore.pay;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PaymentResponseDTO {

    private int totalPrice;
    private int discountPrice;
    private int paymentPrice;

    public static PaymentResponseDTO of(int totalPrice, int discountPrice, int paymentPrice) {
        return PaymentResponseDTO.builder()
                .totalPrice(totalPrice)
                .discountPrice(discountPrice)
                .paymentPrice(paymentPrice)
                .build();
    }

}
