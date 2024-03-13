package com.kjw.sharemore.reivew.dto;

import com.kjw.sharemore.item.normalItem.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.item.normalItem.dto.response.ItemSimpleResponse;
import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.users.dto.UserResponseDTO;
import com.kjw.sharemore.users.dto.UserSimpleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUserPostResponseDTO {

    private ItemSimpleResponse item;

    private String comment;

    private int rating;

    public static ReviewUserPostResponseDTO of(Review review) {
        return ReviewUserPostResponseDTO.builder()
                .item(ItemSimpleResponse.of(review.getItem()))
                .comment(review.getComment())
                .rating(review.getRating())
                .build();
    }
}
