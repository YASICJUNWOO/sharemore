package com.kjw.sharemore.coupon.repository;

import com.kjw.sharemore.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
