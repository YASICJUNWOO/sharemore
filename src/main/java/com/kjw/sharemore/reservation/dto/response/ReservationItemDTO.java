package com.kjw.sharemore.reservation.dto.response;

import com.kjw.sharemore.reservation.Reservation;
import com.kjw.sharemore.users.dto.UserSimpleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationItemDTO {

    private UserSimpleResponseDTO renter;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public static ReservationItemDTO createList(Reservation reservation) {
        return ReservationItemDTO.builder()
                .renter(UserSimpleResponseDTO.of(reservation.getUser()))
                .startDate(reservation.getStartDate())
                .endDate(reservation.getEndDate())
                .build();
    }

    public static List<ReservationItemDTO> createList(List<Reservation> reservation) {
        return reservation.stream()
                .map(ReservationItemDTO::createList)
                .toList();
    }

}
