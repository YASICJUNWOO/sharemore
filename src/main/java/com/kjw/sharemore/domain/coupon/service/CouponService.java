package com.kjw.sharemore.domain.coupon.service;

import com.kjw.sharemore.domain.coupon.entity.Coupon;
import com.kjw.sharemore.domain.coupon.dto.CouponListResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponQueryService couponQueryService;

    public CouponListResponseDTO getCouponList() {
        List<Coupon> couponList = couponQueryService.findAll();
        CouponListResponseDTO couponListResponseDTO = CouponListResponseDTO.of();

        couponList.forEach(
                coupon -> {
                    if(coupon.getCouponType().equals("month"))
                        couponListResponseDTO.addMonthlyCoupon(coupon);
                    else
                        couponListResponseDTO.addCategoryCoupon(coupon);
                }
        );

        return couponListResponseDTO;
    }
}
