package com.kjw.sharemore.pay;

import com.kjw.sharemore.domain.coupon.entity.Coupon;
import com.kjw.sharemore.domain.coupon.service.CouponQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final CouponQueryService couponQueryService;

    public PaymentResponseDTO calculate(PaymentRequestDTO paymentRequestDTO) {

        if(paymentRequestDTO.getCouponId() == null) {
            return PaymentResponseDTO.of(paymentRequestDTO.getPrice(), 0, paymentRequestDTO.getPrice());
        }

        Coupon coupon = couponQueryService.findById(paymentRequestDTO.getCouponId());
        if (coupon.getDiscountType().equals("PERCENT")) {
            return calculatePercent(paymentRequestDTO, coupon.getDiscountRate());
        }

        return calculateAmount(paymentRequestDTO, coupon.getDiscountAmount());
    }

    private PaymentResponseDTO calculateAmount(PaymentRequestDTO paymentRequestDTO, int discountAmount) {
        int totalPrice = paymentRequestDTO.getPrice();
        int paymentPrice = totalPrice - discountAmount;
        return PaymentResponseDTO.of(totalPrice, discountAmount, paymentPrice);
    }

    private PaymentResponseDTO calculatePercent(PaymentRequestDTO paymentRequestDTO, int discountRate) {
        int totalPrice = paymentRequestDTO.getPrice();
        int discountPrice = totalPrice * discountRate / 100;
        int paymentPrice = totalPrice - discountPrice;
        return PaymentResponseDTO.of(totalPrice, discountPrice, paymentPrice);
    }


}
