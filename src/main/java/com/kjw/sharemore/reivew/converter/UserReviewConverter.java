package com.kjw.sharemore.reivew.converter;

import com.kjw.sharemore.reivew.dto.ReviewUserGetResponseDTO;
import com.kjw.sharemore.reivew.dto.ReviewUserPostResponseDTO;
import com.kjw.sharemore.reivew.entity.UserReview;
import com.kjw.sharemore.reivew.dto.ReviewRequestDTO;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.users.converter.UserConverter;
import com.kjw.sharemore.users.entity.Users;

public class UserReviewConverter {

    /**
    * @methodName : toEntity
    * @param :
    * @return :
    * @Description: UserReview 엔티티 저장 시 변환
    * @note:
    **/
    public static UserReview toEntity(ReviewRequestDTO reviewRequestDTO, Users reiviewee, Users reviewer){
        return UserReview.builder()
                .reviewee(reiviewee)
                .reviewer(reviewer)
                .comment(reviewRequestDTO.getComment())
                .rating(reviewRequestDTO.getRating())
                .build();
    }

    public static ReviewResponseDTO toDTO(UserReview userReview) {
        return ReviewResponseDTO.builder()
                .reviewer(UserConverter.toUserResponseDTO(userReview.getReviewer()))
                .reviewee(UserConverter.toUserResponseDTO(userReview.getReviewee()))
                //.item(ItemConverter.toDTO(review.getItem()))
                .comment(userReview.getComment())
                .rating(userReview.getRating())
                .build();
    }

    public static ReviewUserPostResponseDTO toUserPostDTO(UserReview userReview) {
        return ReviewUserPostResponseDTO.builder()
                .reviewee(UserConverter.toUserResponseDTO(userReview.getReviewee()))
                .comment(userReview.getComment())
                .rating(userReview.getRating())
                .build();
    }

    public static ReviewUserGetResponseDTO toUserGetDTO(UserReview userReview) {
        return ReviewUserGetResponseDTO.builder()
                .reviewer(UserConverter.toUserResponseDTO(userReview.getReviewer()))
                .comment(userReview.getComment())
                .rating(userReview.getRating())
                .build();
    }

}
