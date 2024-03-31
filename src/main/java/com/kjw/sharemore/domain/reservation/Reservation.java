package com.kjw.sharemore.domain.reservation;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kjw.sharemore.domain.coupon.entity.UserCoupon;
import com.kjw.sharemore.global.BaseEntity;
import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.users.entity.Users;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private Users renter;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id",nullable = false)
    private Item item;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coupon_id", nullable = true)
    private UserCoupon usedCoupon = null;

    @Column(nullable = false)
    private LocalDateTime startDate;

    @Column(nullable = false)
    private LocalDateTime endDate;

    private int totalPrice;

    private int discountPrice;

    private int finalPrice;

}
