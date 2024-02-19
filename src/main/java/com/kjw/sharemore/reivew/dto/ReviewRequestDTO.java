package com.kjw.sharemore.reivew.dto;

import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.users.entity.Users;
import lombok.Getter;

@Getter
public class ReviewRequestDTO {

    private Long itemId;

    private String comment;

    private int rating;

    public static Review toEntity(ReviewRequestDTO reviewRequestDTO, Item item, Users reviewer) {
        return Review.builder()
                .item(item)
                .reviewer(reviewer)
                .comment(reviewRequestDTO.getComment())
                .rating(reviewRequestDTO.getRating())
                .build();
    }
}
