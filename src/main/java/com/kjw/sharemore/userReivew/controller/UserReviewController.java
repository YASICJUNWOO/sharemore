package com.kjw.sharemore.userReivew.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.userReivew.dto.ReviewRequestDTO;
import com.kjw.sharemore.userReivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.userReivew.service.UserReviewService;
import com.kjw.sharemore.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user-review")
public class UserReviewController {

    private final UserReviewService userReviewService;

    //기본 유저 생성
    //       ('공준우', 'joonoo3@inha.edu', '1234', '성남시 분당구');
    private final Users defaultUser = Users.builder()
            .name("공준우")
            .email("joonoo3@inha.edu")
            .password("1234")
            .address("성남시 분당구")
            .build();

    /**
     * @param :
     * @return :
     * @methodName : getReviewList
     * @Description: 전체 유저 리뷰 조회
     * @note: 추후 필요없으면 삭제
     **/
    @GetMapping
    public ApiResponse<List<ReviewResponseDTO>> getReviewList() {
        return ApiResponse.onSuccess(userReviewService.getReviewList());
    }

    /**
    * @methodName : postReview
    * @param :
    * @return :
    * @Description: 유저 리뷰 추가
    * @note: defaultUser로 일단 리뷰어 설정
    **/
    @PostMapping
    public ApiResponse<ReviewResponseDTO> postReview(@RequestBody ReviewRequestDTO reviewRequestDTO) {
        return ApiResponse.onSuccess(userReviewService.addReview(reviewRequestDTO, defaultUser));
    }
}
