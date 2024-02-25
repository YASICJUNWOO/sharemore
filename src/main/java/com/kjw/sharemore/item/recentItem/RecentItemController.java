package com.kjw.sharemore.item.recentItem;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.item.normalItem.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.users.entity.Users;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item/recent")
public class RecentItemController {

    private final RecentItemService recentItemService;

    @GetMapping
    public ApiResponse<List<ItemResponseBaseDTO>> getRecentItem(@AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(recentItemService.getRecentItem(user));
    }

    @PostMapping
    public ApiResponse<String> saveRecentItem(@RequestParam("itemId") Long itemId,
                                              @AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(recentItemService.saveRecentItem(itemId, user));
    }

}
