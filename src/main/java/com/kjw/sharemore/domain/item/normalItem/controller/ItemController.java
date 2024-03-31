package com.kjw.sharemore.domain.item.normalItem.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.domain.item.normalItem.dto.request.ItemRequestDTO;
import com.kjw.sharemore.domain.item.normalItem.service.ItemBatchService;
import com.kjw.sharemore.domain.item.recentItem.ItemRedisService;
import com.kjw.sharemore.domain.item.normalItem.dto.response.ItemResponseDTO;
import com.kjw.sharemore.domain.item.normalItem.service.ItemQueryService;
import com.kjw.sharemore.domain.item.normalItem.service.ItemService;
import com.kjw.sharemore.domain.users.entity.Users;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final ItemQueryService itemQueryService;
    private final ItemRedisService itemRedisService;
    private final ItemBatchService itemBatchService;

    @GetMapping
    public ApiResponse<List<ItemResponseDTO.Detail>> getItemList() {
        return ApiResponse.onSuccess(itemService.getItemList());
    }

    @GetMapping("/{ItemId}")
    public ApiResponse<ItemResponseDTO.Detail> getItemById(@PathVariable(name = "ItemId") Long ItemId) {
        return ApiResponse.onSuccess(itemService.getItemById(ItemId));
    }

    @PostMapping
    public ApiResponse<ItemResponseDTO.Simple > postItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO,
                                                         @AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(itemService.addItem(itemRequestDTO, user));
    }

    @PatchMapping("/{itemId}")
    public ApiResponse<ItemResponseDTO.Simple> patchItem(@PathVariable("itemId") Long id,
                                                         @Valid @RequestBody ItemRequestDTO itemRequestDTO) {
        return ApiResponse.onSuccess(itemService.updateItem(itemRequestDTO, id));
    }

    @GetMapping("/filter")
    public ApiResponse<List<ItemResponseDTO.Detail>> getFilterItem(@RequestParam(name = "keyword", required = false) String keyword,
                                                                   @RequestParam(name = "sort-type", required = false) String sortType) {
        return ApiResponse.onSuccess(itemService.getItemListByFilter(keyword, sortType));
    }

    @GetMapping("/rank/daily")
    public ApiResponse<List<ItemResponseDTO.Detail>> getDailyRank() {
        return ApiResponse.onSuccess(itemBatchService.getPopularItems());
    }

}
