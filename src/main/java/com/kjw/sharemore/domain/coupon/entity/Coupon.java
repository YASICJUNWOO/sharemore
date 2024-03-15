package com.kjw.sharemore.domain.coupon.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    // 쿠폰의 할인 타입 (금액 할인, 비율 할인)
    private String discountType;

    // 쿠폰의 타입 (월별 쿠폰, 카테고리 쿠폰)
    private String couponType;

    private String couponName;

    private int discountAmount;

    private int discountRate;

    private int minimumPrice;

    private int maximumDiscountAmount;

    private LocalDateTime expireDate;

    @Column(name = "coupon_image_url")
    private String couponImageURL;

}
