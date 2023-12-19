package com.kjw.sharemore.reservation.dto;

import com.kjw.sharemore.item.dto.ItemReservationResponseDTO;
import com.kjw.sharemore.item.dto.ItemUserResponseDTO;
import com.kjw.sharemore.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReservationUserResponseDTO {

    private ItemReservationResponseDTO item;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
