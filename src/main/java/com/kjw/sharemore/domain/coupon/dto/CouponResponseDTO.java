package com.kjw.sharemore.domain.coupon.dto;

import com.kjw.sharemore.domain.coupon.entity.Coupon;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class CouponResponseDTO {

    private Long couponId;

    private String couponName;

    private String couponImageURL;

    private int discountAmount;

    private int discountRate;

    public static CouponResponseDTO of(Coupon coupon) {
        return CouponResponseDTO.builder()
                .couponId(coupon.getCouponId())
                .couponName(coupon.getCouponName())
                .couponImageURL(coupon.getCouponImageURL())
                .discountAmount(coupon.getDiscountAmount())
                .discountRate(coupon.getDiscountRate())
                .build();
    }

}
