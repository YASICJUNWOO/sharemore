package com.kjw.sharemore.reivew.dto;

import com.kjw.sharemore.item.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.users.dto.UserSimpleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewResponseDTO {

    private ItemResponseBaseDTO item;

    private UserSimpleResponseDTO reviewer;

    private String comment;

    private int rating;

    public static ReviewResponseDTO of(Review review) {
        return ReviewResponseDTO.builder()
                .item(ItemResponseBaseDTO.of(review.getItem()))
                .reviewer(UserSimpleResponseDTO.of(review.getReviewer()))
                .comment(review.getComment())
                .rating(review.getRating())
                .build();
    }

}
