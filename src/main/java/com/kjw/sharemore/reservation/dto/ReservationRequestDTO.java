package com.kjw.sharemore.reservation.dto;

import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.users.entity.Users;
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

    //private Users user;
    private String userEmail;

    private String itemName;

    private LocalDateTime startDate;

    private LocalDateTime endDate;
}