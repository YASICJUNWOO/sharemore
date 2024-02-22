package com.kjw.sharemore.reivew.service;

import com.kjw.sharemore.apiPayLoad.code.status.ErrorStatus;
import com.kjw.sharemore.apiPayLoad.exception.GeneralException;
import com.kjw.sharemore.item.repositoty.ItemRepository;
import com.kjw.sharemore.reivew.dto.ReviewRequestDTO;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.reivew.dto.ReviewUserPostResponseDTO;
import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.reivew.repository.ReviewRepository;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public List<ReviewResponseDTO> getReviewList() {
        return reviewRepository.findAll().stream().map(ReviewResponseDTO::of).toList();
    }

    public ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO, Users reviewer) {
        Review entity = ReviewRequestDTO.toEntity(
                reviewRequestDTO,
                itemRepository.findById(reviewRequestDTO.getItemId()).orElseThrow(() -> new GeneralException(ErrorStatus.NO_EXIST_ITEM)),
                reviewer);
        return ReviewResponseDTO.of(reviewRepository.save(entity));
    }

    public List<ReviewResponseDTO> getReviewByReviewee(Users user) {
        return reviewRepository.findAllByItem_Owner(user).stream().map(ReviewResponseDTO::of).toList();
    }

    public List<ReviewUserPostResponseDTO> getReviewByReviewer(Users user) {
        return reviewRepository.findAllByReviewer(user).stream().map(ReviewUserPostResponseDTO::of).toList();
    }

}
