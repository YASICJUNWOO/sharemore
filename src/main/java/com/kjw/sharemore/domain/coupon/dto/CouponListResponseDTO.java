package com.kjw.sharemore.domain.coupon.dto;

import com.kjw.sharemore.domain.coupon.entity.Coupon;
import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
public class CouponListResponseDTO {

    @Builder.Default
    private List<Coupon> monthlyCoupons = new ArrayList<>();

    @Builder.Default
    private List<Coupon> categoryCoupons = new ArrayList<>();

    public static CouponListResponseDTO of() {
        return CouponListResponseDTO.builder()
                .monthlyCoupons(new ArrayList<>())
                .categoryCoupons(new ArrayList<>())
                .build();
    }

    public void addMonthlyCoupon(Coupon coupon) {
        this.monthlyCoupons.add(coupon);
    }

    public void addCategoryCoupon(Coupon coupon) {
        this.categoryCoupons.add(coupon);
    }

}
