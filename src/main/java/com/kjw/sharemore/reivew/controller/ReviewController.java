package com.kjw.sharemore.reivew.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.reivew.dto.ReviewRequestDTO;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.reivew.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ApiResponse<List<ReviewResponseDTO>> getReviewList() {
        return ApiResponse.onSuccess(reviewService.getReviewList());
    }

    @PostMapping
    public ApiResponse<ReviewResponseDTO> postReview(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        return ApiResponse.onSuccess(reviewService.addReview(reviewRequestDTO));
    }
}
