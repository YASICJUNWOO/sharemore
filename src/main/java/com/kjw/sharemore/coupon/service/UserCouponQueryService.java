package com.kjw.sharemore.coupon.service;

import com.kjw.sharemore.coupon.Coupon;
import com.kjw.sharemore.coupon.UserCoupon;
import com.kjw.sharemore.coupon.repository.UserCouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCouponQueryService {

    private final UserCouponRepository userCouponRepository;

    public boolean isExistUserCoupon(Long couponId, Long userId) {
        return userCouponRepository.existsByCoupon_CouponIdAndUser_UserId(couponId, userId);
    }

    public List<UserCoupon> findByUserId(Long userId) {
        return userCouponRepository.findByUser_UserId(userId);
    }

}
