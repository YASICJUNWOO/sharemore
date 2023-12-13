package com.kjw.sharemore.reservation.converter;

import com.kjw.sharemore.item.converter.ItemConverter;
import com.kjw.sharemore.reservation.Reservation;
import com.kjw.sharemore.reservation.dto.ReservationResponseDTO;
import com.kjw.sharemore.users.converter.UserConverter;

public class ReservationConverter {

    public static ReservationResponseDTO toDto(Reservation reservation) {
        return ReservationResponseDTO.builder()
                .user(UserConverter.toUserDetailResponseDTO(reservation.getUser()))
                .item(ItemConverter.toDTO(reservation.getItem()))
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }
}
