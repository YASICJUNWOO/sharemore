package com.kjw.sharemore.reivew.service;

import com.kjw.sharemore.apiPayLoad.code.status.ErrorStatus;
import com.kjw.sharemore.apiPayLoad.exception.GeneralException;
import com.kjw.sharemore.item.repositoty.ItemRepository;
import com.kjw.sharemore.item.service.ItemService;
import com.kjw.sharemore.reivew.dto.ReviewRequestDTO;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.reivew.repository.UserReviewRepository;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.repository.UserRepository;
import com.kjw.sharemore.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final UserReviewRepository userReviewRepository;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public List<ReviewResponseDTO> getReviewList() {
        return userReviewRepository.findAll().stream().map(ReviewResponseDTO::of).toList();
    }

    public ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO, Users reviewer) {
        Review entity = ReviewRequestDTO.toEntity(
                reviewRequestDTO,
                itemRepository.findById(reviewRequestDTO.getItemId()).orElseThrow(()-> new GeneralException(ErrorStatus.NO_EXIST_ITEM)),
                reviewer);
        return ReviewResponseDTO.of(userReviewRepository.save(entity));
    }

    public List<Review> getReviewByReviewee(Users user) {
        return userReviewRepository.findAllByItem_Owner(user);
    }
}
