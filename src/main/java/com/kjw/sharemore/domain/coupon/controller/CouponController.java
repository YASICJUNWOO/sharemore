package com.kjw.sharemore.domain.coupon.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.domain.coupon.dto.CouponListResponseDTO;
import com.kjw.sharemore.domain.coupon.service.CouponQueryService;
import com.kjw.sharemore.domain.coupon.service.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/coupon")
@RequiredArgsConstructor
public class CouponController {

    private final CouponQueryService couponQueryService;
    private final CouponService couponService;

    @GetMapping
    public ApiResponse<CouponListResponseDTO> getCouponList() {
        return ApiResponse.onSuccess(couponService.getCouponList());
    }

}
