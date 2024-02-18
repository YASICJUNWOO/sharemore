package com.kjw.sharemore.reservation.dto.response;

import com.kjw.sharemore.item.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.reservation.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationUserResponseDTO {

    private ItemResponseBaseDTO item;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public static ReservationUserResponseDTO of(Reservation reservation) {
        return ReservationUserResponseDTO.builder()
                .item(ItemResponseBaseDTO.of(reservation.getItem()))
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }

    public static List<ReservationUserResponseDTO> createList(List<Reservation> reservation) {
        return reservation.stream()
                .map(ReservationUserResponseDTO::of)
                .toList();
    }

}
