package com.kjw.sharemore.domain.coupon.repository;

import com.kjw.sharemore.domain.coupon.entity.UserCoupon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserCouponRepository extends JpaRepository<UserCoupon, Long> {

    boolean existsByCoupon_CouponIdAndUser_UserId(Long couponId, Long userId);

    List<UserCoupon> findByUser_UserId(Long userId);

    Optional<UserCoupon> findByUser_UserIdAndCoupon_CouponId(Long userId, Long couponId);
}
