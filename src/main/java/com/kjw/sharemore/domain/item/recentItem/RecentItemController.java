package com.kjw.sharemore.domain.item.recentItem;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.domain.item.normalItem.dto.response.ItemResponseDTO;
import com.kjw.sharemore.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item/recent")
public class RecentItemController {

    private final ItemRedisService itemRedisService;

    @GetMapping
    public ApiResponse<List<ItemResponseDTO.Simple>> getRecentItem(@AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(itemRedisService.getRecentItem(user));
    }

    @PostMapping
    public ApiResponse<String> saveRecentItem(@RequestParam("itemId") Long itemId,
                                              @AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(itemRedisService.saveRecentItem(itemId, user));
    }

    @GetMapping("/hot")
    public ApiResponse<List<HotKeyWordDTO>> getHotKeyWord() {
        return ApiResponse.onSuccess(itemRedisService.getHotKeyWord());
    }

}
