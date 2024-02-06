package com.kjw.sharemore.item.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.item.dto.ItemRequestDTO;
import com.kjw.sharemore.item.dto.ItemResponseDTO;
import com.kjw.sharemore.item.dto.search.SearchDTO;
import com.kjw.sharemore.item.service.ItemService;
import com.kjw.sharemore.item.service.SearchService;
import com.kjw.sharemore.users.entity.Users;
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

    //기본 유저 생성
    //       ('공준우', 'joonoo3@inha.edu', '1234', '성남시 분당구');
    private final Users defaultUser = Users.builder()
            .name("공준우")
            .email("joonoo3@inha.edu")
            .password("1234")
            .address("성남시 분당구")
            .build();

    /**
    * @methodName : getItemList
    * @param :
    * @return :
    * @Description: 전체 아이템 조회
    * @note:
    **/
    @GetMapping
    public ApiResponse<List<ItemResponseDTO>> getItemList() {
        return ApiResponse.onSuccess(itemService.getItemList());
    }

    /**
    * @methodName : getItemById
    * @param :
    * @return :
    * @Description: 아이템 아이디로 아이템 조회
    * @note:
    **/
    @GetMapping("/{ItemId}")
    public ApiResponse<ItemResponseDTO> getItemById(@PathVariable(name = "ItemId") Long ItemId) {
        return ApiResponse.onSuccess(itemService.getItemResponseById(ItemId));
    }

    /*@PostMapping("/search")
    public ApiResponse<List<ItemResponseDTO>> searchItemList(@RequestBody SearchDTO searchDTO) {
        return ApiResponse.onSuccess(searchService.searchItem(searchDTO));
    }*/

    /**
    * @methodName : postItem
    * @param :
    * @return :
    * @Description: 아이템 추가
    * @note:
    **/
    @PostMapping
    public ApiResponse<ItemResponseDTO> postItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO) {
        log.info("itemRequestDTO : {}", itemRequestDTO);
        return ApiResponse.onSuccess(itemService.addItem(itemRequestDTO, defaultUser));
    }

    /**
    * @methodName : patchItem
    * @param :
     * @path : itemId (아이템 아이디)
    * @return :
    * @Description: 아이템 수정
    * @note: 추후 이미지 수정 추가
    **/
    @PatchMapping("/{itemId}")
    public ApiResponse<ItemResponseDTO> patchItem(@PathVariable("itemId") Long id,
                                                  @Valid @RequestBody ItemRequestDTO itemRequestDTO) {
        log.info("itemRequestDTO : {}", itemRequestDTO);
        return ApiResponse.onSuccess(itemService.updateItem(itemRequestDTO, id));
    }

}
