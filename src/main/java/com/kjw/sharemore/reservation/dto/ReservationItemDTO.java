package com.kjw.sharemore.reservation.dto;

import com.kjw.sharemore.item.dto.ItemReservationResponseDTO;
import com.kjw.sharemore.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationItemDTO {

    private UserResponseDTO renter;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
