package com.kjw.sharemore.item.normalItem.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.item.normalItem.entity.ItemDocument;
import com.kjw.sharemore.item.normalItem.service.ItemSearchService;
import com.kjw.sharemore.item.normalItem.ItemDataInitializer;
import com.kjw.sharemore.item.recentItem.RecentItemService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/item/search")
public class ItemSearchController {

    private final ItemSearchService itemSearchService;

    private final ItemDataInitializer ItemDataInitializer;

    private final RecentItemService recentItemService;

    @PostConstruct
    public void init() {
        ItemDataInitializer.init();
    }

    /**
    * @Description: 아이템 document 검색
    * @param : keyword
    * @path :
    * @body :
    * @return :
    **/
    @GetMapping
    public ApiResponse<List<ItemDocument>> search(@RequestParam("keyword") String keyword) {
        recentItemService.saveHotKeyWord(keyword);
        return ApiResponse.onSuccess(itemSearchService.getItemByName(keyword));
    }

    /**
    * @Description: 아이템 document 생성
    * @param :
    * @path :
    * @body : ItemDocument
    * @return :
    **/
    @PostMapping
    public ApiResponse<ItemDocument> create(@RequestBody ItemDocument itemDocument) {
        return ApiResponse.onSuccess(itemSearchService.createItem(itemDocument));
    }

    /**
    * @Description: 아이템 document 삭제
    * @param :
    * @path :
    * @body :
    * @return :
    **/
    @DeleteMapping
    public ApiResponse<String> delete() {
        itemSearchService.deleteItemIndex();
        return ApiResponse.onSuccess("삭제 완료");
    }

}
