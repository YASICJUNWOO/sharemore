package com.kjw.sharemore.reservation.dto;

import com.kjw.sharemore.item.dto.ItemResponseDTO;
import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.users.dto.UserResponseDTO;
import com.kjw.sharemore.users.entity.Users;
import jakarta.persistence.*;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public class ReservationResponseDTO {

    private UserResponseDTO user;

    private ItemResponseDTO item;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

}
