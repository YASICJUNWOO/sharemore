package com.kjw.sharemore.reservation.dto.response;

import com.kjw.sharemore.item.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.reservation.Reservation;
import com.kjw.sharemore.users.dto.UserSimpleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationResponseDTO {

    private Long id;

    private UserSimpleResponseDTO renter;

    private ItemResponseBaseDTO item;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public static ReservationResponseDTO of(Reservation reservation) {
        return ReservationResponseDTO.builder()
                .id(reservation.getReservationId())
                .renter(UserSimpleResponseDTO.of(reservation.getUser()))
                .item(ItemResponseBaseDTO.of(reservation.getItem()))
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }

}
