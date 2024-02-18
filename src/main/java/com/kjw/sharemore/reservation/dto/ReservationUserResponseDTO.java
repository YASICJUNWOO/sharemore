package com.kjw.sharemore.reservation.dto;

import com.kjw.sharemore.item.dto.response.ItemResponseBaseDTO;
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

    private ItemResponseBaseDTO item;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
