package com.kjw.sharemore.domain.coupon.service;

import com.kjw.sharemore.domain.coupon.entity.Coupon;
import com.kjw.sharemore.domain.coupon.dto.CouponResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponQueryService couponQueryService;

    public CouponResponseDTO getCouponList() {
        List<Coupon> couponList = couponQueryService.findAll();
        CouponResponseDTO couponResponseDTO = CouponResponseDTO.of();

        couponList.forEach(
                coupon -> {
                    if(coupon.getCouponType().equals("month"))
                        couponResponseDTO.addMonthlyCoupon(coupon);
                    else
                        couponResponseDTO.addCategoryCoupon(coupon);
                }
        );

        return couponResponseDTO;
    }
}
