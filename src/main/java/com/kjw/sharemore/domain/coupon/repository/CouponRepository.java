package com.kjw.sharemore.domain.coupon.repository;

import com.kjw.sharemore.domain.coupon.entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
}
