package com.kjw.sharemore.domain.coupon.service;

import com.kjw.sharemore.domain.coupon.entity.UserCoupon;
import com.kjw.sharemore.domain.coupon.repository.UserCouponRepository;
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

    public UserCoupon findByUserIdAndCouponId(Long userId, Long couponId) {
        return userCouponRepository.findByUser_UserIdAndCoupon_CouponId(userId, couponId).orElseThrow(() -> new IllegalArgumentException("쿠폰이 존재하지 않습니다."));
    }

}
