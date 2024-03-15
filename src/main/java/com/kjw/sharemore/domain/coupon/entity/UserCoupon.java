package com.kjw.sharemore.domain.coupon.entity;

import com.kjw.sharemore.domain.users.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCoupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userCouponId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id")
    private Coupon coupon;

    @Builder.Default
    private boolean used = false;

    public static UserCoupon of(Users user, Coupon coupon) {
        return UserCoupon.builder()
                .user(user)
                .coupon(coupon)
                .build();
    }

    public void useCoupon() {
        this.used = true;
    }

}
