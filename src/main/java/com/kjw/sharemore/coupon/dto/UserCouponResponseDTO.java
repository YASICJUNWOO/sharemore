package com.kjw.sharemore.coupon.dto;

import com.kjw.sharemore.coupon.UserCoupon;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserCouponResponseDTO {

    private Long couponId;

    private String couponName;

    private String couponImageURL;

    public static UserCouponResponseDTO of(UserCoupon coupon) {
        return UserCouponResponseDTO.builder()
                .couponImageURL(coupon.getCoupon().getCouponImageURL())
                .couponId(coupon.getCoupon().getCouponId())
                .couponName(coupon.getCoupon().getCouponName())
                .build();
    }

}
