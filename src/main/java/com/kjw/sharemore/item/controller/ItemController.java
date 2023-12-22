package com.kjw.sharemore.item.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.item.dto.ItemRequestDTO;
import com.kjw.sharemore.item.dto.ItemResponseDTO;
import com.kjw.sharemore.item.dto.search.SearchDTO;
import com.kjw.sharemore.item.service.ItemService;
import com.kjw.sharemore.item.service.SearchService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/item")
@RequiredArgsConstructor
@Slf4j
public class ItemController {

    private final ItemService itemService;
    private final SearchService searchService;

    @GetMapping
    public ApiResponse<List<ItemResponseDTO>> getItemList() {
        return ApiResponse.onSuccess(itemService.getItemList());
    }

    /**
    * @methodName : getItemByCategory
    * @param : category (카테고리)
    * @return : List<ItemResponseDTO>
    * @Description: 카테고리별 아이템 조회
    * @note:
    **/
   /* @GetMapping("/{category}")
    public ApiResponse<List<ItemResponseDTO>> getItemByCategory(@PathVariable(name = "category") String category) {
        return ApiResponse.onSuccess(itemService.getItemByCategory(category));
    }*/

    @PostMapping("/search")
    public ApiResponse<List<ItemResponseDTO>> searchItemList(@RequestBody SearchDTO searchDTO) {
        return ApiResponse.onSuccess(searchService.searchItem(searchDTO));
    }

    @PostMapping
    public ApiResponse<ItemResponseDTO> postItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO) {
        return ApiResponse.onSuccess(itemService.addItem(itemRequestDTO));
    }

}
