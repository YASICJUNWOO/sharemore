package com.kjw.sharemore.reservation.dto;

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

    //private Users user;]
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 아닙니다.")
    private String userEmail;

    @NotBlank
    private String itemName;

    @NotNull
    @FutureOrPresent(message = "현재 시간 이후여야 합니다.")
    private LocalDateTime startDate;

    @NotNull
    @Future(message = "현재 시간 이후여야 합니다.")
    private LocalDateTime endDate;
}
