package com.kjw.sharemore.reservation.dto.request;

import com.kjw.sharemore.item.normalItem.entity.Item;
import com.kjw.sharemore.reservation.Reservation;
import com.kjw.sharemore.users.entity.Users;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReservationRequestDTO {

    @NotNull
    @FutureOrPresent(message = "현재 시간 이후여야 합니다.")
    private LocalDateTime startDate;

    @NotNull
    @Future(message = "현재 시간 이후여야 합니다.")
    private LocalDateTime endDate;

    public static Reservation toEntity(ReservationRequestDTO dto, Users user, Item item) {
        return Reservation.builder()
                .user(user)
                .item(item)
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
    }
}
