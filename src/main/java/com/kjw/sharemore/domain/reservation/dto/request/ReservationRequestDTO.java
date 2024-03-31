package com.kjw.sharemore.domain.reservation.dto.request;

import com.kjw.sharemore.domain.coupon.entity.UserCoupon;
import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.reservation.Reservation;
import com.kjw.sharemore.domain.users.entity.Users;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRequestDTO {

    private Long itemId;

    @NotNull
    @FutureOrPresent(message = "현재 시간 이후여야 합니다.")
    private LocalDateTime startDate;

    @NotNull
    @Future(message = "현재 시간 이후여야 합니다.")
    private LocalDateTime endDate;

    private Long couponId;

    private int totalPrice;

    private int discountPrice;

    private int paymentPrice;

    public static Reservation toEntity(ReservationRequestDTO dto, Users user, Item item, UserCoupon userCoupon) {
        return Reservation.builder()
                .renter(user)
                .item(item)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .usedCoupon(userCoupon)
                .totalPrice(dto.getTotalPrice())
                .discountPrice(dto.getDiscountPrice())
                .finalPrice(dto.getPaymentPrice())
                .build();
    }
}
