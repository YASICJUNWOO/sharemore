package com.kjw.sharemore.item.normalItem.dto.response;

import com.kjw.sharemore.item.normalItem.entity.Item;
import com.kjw.sharemore.users.dto.UserSimpleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
public class ItemResponseBaseDTO {

    private Long id;

    private String name;

    private UserSimpleResponseDTO owner;

    private String description;

    private String category;

    private int price;

    private String itemImage;

    public static ItemResponseBaseDTO of(Item item) {
        return ItemResponseBaseDTO.builder()
                .id(item.getItemId())
                .name(item.getName())
                .owner(UserSimpleResponseDTO.of(item.getOwner()))
                .description(item.getDescription())
                .category(item.getCategory())
                .price(item.getPrice())
                .itemImage(item.getItemImage())
                .build();
    }

}
