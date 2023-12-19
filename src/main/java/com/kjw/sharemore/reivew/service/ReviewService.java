package com.kjw.sharemore.reivew.service;

import com.kjw.sharemore.reivew.converter.ReviewConverter;
import com.kjw.sharemore.reivew.dto.ReviewRequestDTO;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.reivew.repository.ReviewRepository;
import com.kjw.sharemore.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserService userService;

    public ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO) {
        Review entity = ReviewConverter.toEntity(
                reviewRequestDTO,
                userService.getUserByEmail(reviewRequestDTO.getRevieweeEmail()),
                userService.getUserByEmail(reviewRequestDTO.getReviewerEmail())
        );
        return ReviewConverter.toDTO(reviewRepository.save(entity));
    }

    public List<ReviewResponseDTO> getReviewList() {
        return reviewRepository.findAll().stream().map(ReviewConverter::toDTO).toList();
    }
}
