package com.kjw.sharemore.domain.reservation.dto.response;

import com.kjw.sharemore.domain.reservation.Reservation;
import com.kjw.sharemore.domain.users.dto.UserResponseDTO;
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

    private UserResponseDTO.Simple renter;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public static ReservationItemDTO createList(Reservation reservation) {
        return ReservationItemDTO.builder()
                .renter(UserResponseDTO.Simple.of(reservation.getRenter()))
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
