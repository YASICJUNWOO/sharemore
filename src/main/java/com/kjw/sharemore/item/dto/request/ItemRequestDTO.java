package com.kjw.sharemore.item.dto.request;

import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.users.entity.Users;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

/**
* @Description: 아이템 등록 요청 DTO
**/

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequestDTO {

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String category;

    @PositiveOrZero
    private int price;

    private String itemImage;

    public static Item toEntity(ItemRequestDTO itemRequestDTO, Users user) {
        return Item.builder()
                .name(itemRequestDTO.getName())
                .description(itemRequestDTO.getDescription())
                .category(itemRequestDTO.getCategory())
                .price(itemRequestDTO.getPrice())
                .itemImage(itemRequestDTO.getItemImage())
                .owner(user)
                .build();
    }

}
