package com.kjw.sharemore.domain.item.normalItem.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.domain.item.recentItem.ItemRedisService;
import com.kjw.sharemore.domain.item.normalItem.entity.ItemDocument;
import com.kjw.sharemore.domain.item.normalItem.service.ItemSearchService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item/search")
public class ItemSearchController {

    private final ItemSearchService itemSearchService;

    private final com.kjw.sharemore.domain.item.normalItem.ItemDataInitializer ItemDataInitializer;

    private final ItemRedisService itemRedisService;

    @PostConstruct
    public void init() {
        ItemDataInitializer.init();
    }

    @PostMapping
    public ApiResponse<ItemDocument> create(@RequestBody ItemDocument itemDocument) {
        return ApiResponse.onSuccess(itemSearchService.createItem(itemDocument));
    }

    @DeleteMapping
    public ApiResponse<String> delete() {
        itemSearchService.deleteItemIndex();
        return ApiResponse.onSuccess("삭제 완료");
    }

}
