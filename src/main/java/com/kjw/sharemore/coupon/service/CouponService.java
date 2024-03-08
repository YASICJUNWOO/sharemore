package com.kjw.sharemore.coupon.service;

import com.kjw.sharemore.coupon.Coupon;
import com.kjw.sharemore.coupon.dto.CouponListDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponService {

    private final CouponQueryService couponQueryService;

    public CouponListDTO getCouponList() {
        List<Coupon> couponList = couponQueryService.findAll();
        CouponListDTO couponListDTO = CouponListDTO.of();

        couponList.forEach(
                coupon -> {
                    if(coupon.getCouponType().equals("month"))
                        couponListDTO.addMonthlyCoupon(coupon);
                    else
                        couponListDTO.addCategoryCoupon(coupon);
                }
        );

        return couponListDTO;
    }
}
