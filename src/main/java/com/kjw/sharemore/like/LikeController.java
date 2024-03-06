package com.kjw.sharemore.like;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.users.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ApiResponse<String> addLike(@AuthenticationPrincipal Users user,
                                       @RequestParam Long itemId) {
        likeService.addLike(user, itemId);
        return ApiResponse.onSuccess("좋아요 추가 완료");
    }

}
