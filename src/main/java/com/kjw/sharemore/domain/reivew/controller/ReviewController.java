package com.kjw.sharemore.domain.reivew.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.domain.reivew.dto.ReviewRequestDTO;
import com.kjw.sharemore.domain.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.domain.reivew.service.ReviewService;
import com.kjw.sharemore.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping()
    public ApiResponse<ReviewResponseDTO.Simple> postReview(@RequestBody ReviewRequestDTO reviewRequestDTO,
                                                     @AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(reviewService.addReview(reviewRequestDTO,user));
    }

}
