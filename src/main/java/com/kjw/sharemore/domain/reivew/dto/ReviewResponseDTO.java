package com.kjw.sharemore.domain.reivew.dto;

import com.kjw.sharemore.domain.item.normalItem.dto.response.ItemResponseDTO;
import com.kjw.sharemore.domain.reivew.entity.Review;
import com.kjw.sharemore.domain.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewResponseDTO {

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Simple {
        private Long reviewId;
        private UserResponseDTO.Simple reviewer;
        private String comment;
        private int rating;

        public static ReviewResponseDTO.Simple of(Review review) {
            return ReviewResponseDTO.Simple.builder()
                    .reviewId(review.getReviewId())
                    .reviewer(UserResponseDTO.Simple.of(review.getReviewer()))
                    .comment(review.getComment())
                    .rating(review.getRating())
                    .build();
        }
    }

    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Detail {
        private Long reviewId;
        private ItemResponseDTO.Simple item;
        private UserResponseDTO.Simple reviewer;
        private String comment;
        private int rating;

        public static ReviewResponseDTO.Detail of(Review review) {
            return ReviewResponseDTO.Detail.builder()
                    .reviewId(review.getReviewId())
                    .item(ItemResponseDTO.Simple.of(review.getItem()))
                    .reviewer(UserResponseDTO.Simple.of(review.getReviewer()))
                    .comment(review.getComment())
                    .rating(review.getRating())
                    .build();
        }
    }


}
