package com.kjw.sharemore.coupon.service;

import com.kjw.sharemore.coupon.Coupon;
import com.kjw.sharemore.coupon.UserCoupon;
import com.kjw.sharemore.coupon.dto.UserCouponResponseDTO;
import com.kjw.sharemore.coupon.repository.CouponRepository;
import com.kjw.sharemore.coupon.repository.UserCouponRepository;
import com.kjw.sharemore.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserCouponService {

    private final UserCouponRepository userCouponRepository;
    private final CouponRepository couponRepository;
    private final UserCouponQueryService userCouponQueryService;
    private final CouponQueryService couponQueryService;

    public List<UserCouponResponseDTO> getCouponList(Users user) {
        return userCouponQueryService.findByUserId(user.getUserId()).stream()
                .map(UserCouponResponseDTO::of)
                .toList();
    }

    public String publishCoupon(Users user, Long couponId) {

        if(userCouponQueryService.isExistUserCoupon(couponId, user.getUserId())) {
            throw new IllegalArgumentException("이미 발급된 쿠폰입니다.");
        }

        Coupon coupon = couponQueryService.findById(couponId);

        UserCoupon userCoupon = UserCoupon.of(user, coupon);
        userCouponRepository.save(userCoupon);

        return "쿠폰이 발급되었습니다.";
    }

}
