package com.kjw.sharemore.item.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.item.entity.ItemDocument;
import com.kjw.sharemore.item.initializer;
import com.kjw.sharemore.item.service.ItemSearchService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item/search")
public class ItemSearchController {

    private final ItemSearchService itemSearchService;

    private final initializer initializer;

    @PostConstruct
    public void init() {
        initializer.init();
    }

    @GetMapping
    public ApiResponse<List<ItemDocument>> search(@RequestParam("keyword") String keyword) {
        return ApiResponse.onSuccess(itemSearchService.getItem(keyword));
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
