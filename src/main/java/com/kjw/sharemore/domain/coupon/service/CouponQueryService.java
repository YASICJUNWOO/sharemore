package com.kjw.sharemore.domain.coupon.service;

import com.kjw.sharemore.domain.coupon.entity.Coupon;
import com.kjw.sharemore.domain.coupon.repository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CouponQueryService {

    private final CouponRepository couponRepository;

    public List<Coupon> findAll() {
        return couponRepository.findAll();
    }

    public Coupon findById(Long couponId) {
        return couponRepository.findById(couponId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 쿠폰입니다."));
    }

}
