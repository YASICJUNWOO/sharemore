package com.kjw.sharemore.item.dto;

import com.kjw.sharemore.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemReservationResponseDTO {

    private UserResponseDTO owner;

    private String name;

    private String description;

    private String category;

    private int price;

    private String itemImage;

}
