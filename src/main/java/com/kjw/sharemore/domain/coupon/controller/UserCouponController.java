package com.kjw.sharemore.domain.coupon.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.domain.coupon.dto.CouponResponseDTO;
import com.kjw.sharemore.domain.coupon.service.UserCouponService;
import com.kjw.sharemore.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupon-user")
@RequiredArgsConstructor
public class UserCouponController {

    private final UserCouponService userCouponService;

    @GetMapping
    public ApiResponse<List<CouponResponseDTO>> getCouponList(@AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(userCouponService.getCouponList(user));
    }

    @PostMapping("/publish")
    public ApiResponse<String> publishCoupon(@AuthenticationPrincipal Users user,
                                     @RequestParam(name = "couponId") Long couponId) {
        return ApiResponse.onSuccess(userCouponService.publishCoupon(user, couponId));
    }

}
