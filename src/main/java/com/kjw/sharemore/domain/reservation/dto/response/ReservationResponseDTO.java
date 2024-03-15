package com.kjw.sharemore.domain.reservation.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kjw.sharemore.domain.coupon.dto.UserCouponResponseDTO;
import com.kjw.sharemore.domain.item.normalItem.dto.response.ItemResponseDTO;
import com.kjw.sharemore.domain.reservation.Reservation;
import com.kjw.sharemore.domain.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

public class ReservationResponseDTO {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Simple {

        private Long id;
        private UserResponseDTO.Simple renter;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime startDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime endDate;

        public static ReservationResponseDTO.Simple of(Reservation reservation) {
            return ReservationResponseDTO.Simple.builder()
                    .id(reservation.getId())
                    .renter(UserResponseDTO.Simple.of(reservation.getRenter()))
                    .startDate(reservation.getStartDate())
                    .endDate(reservation.getEndDate())
                    .build();
        }

    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Detail {

        private Long id;
        private UserResponseDTO.Simple renter;
        private ItemResponseDTO.Simple item;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime startDate;
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime endDate;

        private UserCouponResponseDTO usedCoupon;

        private LocalDateTime createdAt;

        public static ReservationResponseDTO.Detail of(Reservation reservation) {
            return ReservationResponseDTO.Detail.builder()
                    .id(reservation.getId())
                    .renter(UserResponseDTO.Simple.of(reservation.getRenter()))
                    .item(ItemResponseDTO.Simple.of(reservation.getItem()))
                    .startDate(reservation.getStartDate())
                    .endDate(reservation.getEndDate())
                    .createdAt(reservation.getCreatedAt())
                    .usedCoupon(UserCouponResponseDTO.of(reservation.getUsedCoupon()))
                    .build();
        }

    }


}
