package com.kjw.sharemore.coupon.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.coupon.Coupon;
import com.kjw.sharemore.coupon.dto.CouponListDTO;
import com.kjw.sharemore.coupon.service.CouponQueryService;
import com.kjw.sharemore.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponQueryService couponQueryService;
    private final CouponService couponService;

    @GetMapping
    public ApiResponse<CouponListDTO> getCouponList() {
        return ApiResponse.onSuccess(couponService.getCouponList());
    }

}
