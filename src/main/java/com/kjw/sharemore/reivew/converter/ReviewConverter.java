package com.kjw.sharemore.reivew.converter;

import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.reivew.dto.ReviewRequestDTO;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.users.converter.UserConverter;
import com.kjw.sharemore.users.entity.Users;

public class ReviewConverter {

    public static Review toEntity(ReviewRequestDTO reviewRequestDTO, Users reiviewee, Users reviewer){
        return Review.builder()
                //.item(reviewRequestDTO.getItem())
                .reviewee(reiviewee)
                .reviewer(reviewer)
                .comment(reviewRequestDTO.getComment())
                .rating(reviewRequestDTO.getRating())
                .build();
    }

    public static ReviewResponseDTO toDTO(Review review) {
        return ReviewResponseDTO.builder()
                .reviewer(UserConverter.toUserDetailResponseDTO(review.getReviewer()))
                .reviewee(UserConverter.toUserDetailResponseDTO(review.getReviewee()))
                //.item(ItemConverter.toDTO(review.getItem()))
                .comment(review.getComment())
                .rating(review.getRating())
                .build();
    }
}
