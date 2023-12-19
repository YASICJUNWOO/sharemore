package com.kjw.sharemore.reservation.converter;

import com.kjw.sharemore.item.converter.ItemConverter;
import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.reservation.Reservation;
import com.kjw.sharemore.reservation.dto.ReservationItemDTO;
import com.kjw.sharemore.reservation.dto.ReservationRequestDTO;
import com.kjw.sharemore.reservation.dto.ReservationResponseDTO;
import com.kjw.sharemore.reservation.dto.ReservationUserResponseDTO;
import com.kjw.sharemore.users.converter.UserConverter;
import com.kjw.sharemore.users.entity.Users;

public class ReservationConverter {

    public static ReservationResponseDTO toDto(Reservation reservation) {
        return ReservationResponseDTO.builder()
                .renter(UserConverter.toUserResponseDTO(reservation.getUser()))
                .item(ItemConverter.toItemReservationDTO(reservation.getItem()))
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }

    public static Reservation toEntity(ReservationRequestDTO reservationRequestDTO, Users user, Item item) {
        return Reservation.builder()
                .user(user)
                .item(item)
                .startDate(reservationRequestDTO.getStartDate())
                .endDate(reservationRequestDTO.getEndDate())
                .build();
    }

    public static ReservationUserResponseDTO toReservationUserResponseDTO(Reservation reservation){
        return ReservationUserResponseDTO.builder()
                .item(ItemConverter.toItemReservationDTO(reservation.getItem()))
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }

    public static ReservationItemDTO toReservationItemDTO(Reservation reservation){
        return ReservationItemDTO.builder()
                .renter(UserConverter.toUserResponseDTO(reservation.getUser()))
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }
}
