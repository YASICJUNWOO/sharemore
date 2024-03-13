package com.kjw.sharemore.item.normalItem.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.kjw.sharemore.item.normalItem.entity.Item;
import com.kjw.sharemore.users.dto.UserSimpleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@SuperBuilder
public class ItemResponseBaseDTO extends ItemSimpleResponse {

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDateTime createdAt;

    @Builder.Default
    private boolean isLike = false;

    @Builder.Default
    private Integer likeCount = 0;

    private Long viewCount;

    public static ItemResponseBaseDTO of(Item item, Long viewCount ) {
        return ItemResponseBaseDTO.builder()
                .id(item.getItemId())
                .name(item.getName())
                .owner(UserSimpleResponseDTO.of(item.getOwner()))
                .description(item.getDescription())
                .category(item.getCategory())
                .price(item.getPrice())
                .itemImage(item.getItemImage())
                .createdAt(item.getCreatedAt())
                .likeCount(item.getLikeCount())
                .viewCount(viewCount)
                .build();
    }

}
