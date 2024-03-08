package com.kjw.sharemore.coupon.repository;

import com.kjw.sharemore.coupon.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {

    boolean existsByCoupon_CouponIdAndUser_UserId(Long couponId, Long userId);

    List<UserCoupon> findByUser_UserId(Long userId);
}
