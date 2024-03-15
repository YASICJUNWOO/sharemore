package com.kjw.sharemore.domain.reivew.service;

import com.kjw.sharemore.apiPayLoad.code.status.ErrorStatus;
import com.kjw.sharemore.apiPayLoad.exception.GeneralException;
import com.kjw.sharemore.domain.item.normalItem.repositoty.ItemRepository;
import com.kjw.sharemore.domain.reivew.dto.ReviewRequestDTO;
import com.kjw.sharemore.domain.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.domain.reivew.entity.Review;
import com.kjw.sharemore.domain.reivew.repository.ReviewRepository;
import com.kjw.sharemore.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ItemRepository itemRepository;

    public ReviewResponseDTO.Simple addReview(ReviewRequestDTO reviewRequestDTO, Users reviewer) {
        Review entity = ReviewRequestDTO.toEntity(
                reviewRequestDTO,
                itemRepository.findById(reviewRequestDTO.getItemId()).orElseThrow(() -> new GeneralException(ErrorStatus.NO_EXIST_ITEM)),
                reviewer);
        return ReviewResponseDTO.Simple.of(reviewRepository.save(entity));
    }

}
