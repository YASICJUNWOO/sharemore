package com.kjw.sharemore.item.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.item.ItemDataInitializer;
import com.kjw.sharemore.item.entity.ItemDocument;
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

    private final ItemDataInitializer ItemDataInitializer;

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
