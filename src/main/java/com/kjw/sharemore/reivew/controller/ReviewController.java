package com.kjw.sharemore.reivew.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.reivew.dto.ReviewRequestDTO;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.reivew.service.ReviewService;
import com.kjw.sharemore.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/review")
public class ReviewController {

    private final ReviewService reviewService;

   /**
   * @Description: 전체 리뷰 조회
   * @param :
   * @path :
   * @body :
   * @return :
   **/
    @GetMapping
    public ApiResponse<List<ReviewResponseDTO>> getReviewList() {
        return ApiResponse.onSuccess(reviewService.getReviewList());
    }

    /**
    * @Description:  리뷰 등록
    * @param :
    * @path :
    * @body : reviewRequestDTO
    * @return :
    **/
    @PostMapping()
    public ApiResponse<ReviewResponseDTO> postReview(@RequestBody ReviewRequestDTO reviewRequestDTO,
                                                     @AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(reviewService.addReview(reviewRequestDTO,user));
    }

}
