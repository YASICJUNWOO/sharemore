package com.kjw.sharemore.coupon;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.cglib.core.Local;

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

    private String discountType;

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
