package com.kjw.sharemore.reivew.service;

import com.kjw.sharemore.reivew.converter.UserReviewConverter;
import com.kjw.sharemore.reivew.dto.ReviewRequestDTO;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.reivew.entity.UserReview;
import com.kjw.sharemore.reivew.repository.UserReviewRepository;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserReviewService {

    private final UserReviewRepository userReviewRepository;
    private final UserService userService;

    public List<ReviewResponseDTO> getReviewList() {
        return userReviewRepository.findAll().stream().map(UserReviewConverter::toDTO).toList();
    }

    public ReviewResponseDTO addReview(ReviewRequestDTO reviewRequestDTO, Users reviewee) {
        UserReview entity = UserReviewConverter.toEntity(
                reviewRequestDTO,
                userService.getUserByEmail(reviewRequestDTO.getRevieweeEmail()),
                userService.getUserByEmail(reviewee.getEmail())
        );
        return UserReviewConverter.toDTO(userReviewRepository.save(entity));
    }
}
