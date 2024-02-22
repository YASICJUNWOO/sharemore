package com.kjw.sharemore.item.normalItem.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.item.normalItem.dto.request.ItemRequestDTO;
import com.kjw.sharemore.item.normalItem.dto.response.ItemResponseDTO;
import com.kjw.sharemore.item.normalItem.service.ItemService;
import com.kjw.sharemore.users.entity.Users;
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

    /**
    * @Description: 아이템 리스트 조회
    * @param :
    * @path :
    * @body :
    * @return :
    **/
    @GetMapping
    public ApiResponse<List<ItemResponseDTO>> getItemList() {
        return ApiResponse.onSuccess(itemService.getItemList());
    }

    /**
    * @Description: 아이템 상세 조회
    * @param :
    * @path : ItemId (아이템 아이디)
    * @body :
    * @return :
    **/
    @GetMapping("/{ItemId}")
    public ApiResponse<ItemResponseDTO> getItemById(@PathVariable(name = "ItemId") Long ItemId) {
        return ApiResponse.onSuccess(itemService.getItemResponseById(ItemId));
    }

    /**
    * @Description: 아이템 등록
    * @param :
    * @path :
    * @body : ItemRequestDTO
    * @return :
    **/
    @PostMapping
    public ApiResponse<ItemResponseDTO> postItem(@Valid @RequestBody ItemRequestDTO itemRequestDTO,
                                                 @AuthenticationPrincipal Users user) {
        return ApiResponse.onSuccess(itemService.addItem(itemRequestDTO, user));
    }

    /**
    * @Description: 아이템 수정
    * @param :
    * @path :  ItemId (아이템 아이디)
    * @body : ItemRequestDTO
    * @return :
    **/
    @PatchMapping("/{itemId}")
    public ApiResponse<ItemResponseDTO> patchItem(@PathVariable("itemId") Long id,
                                                  @Valid @RequestBody ItemRequestDTO itemRequestDTO) {
        return ApiResponse.onSuccess(itemService.updateItem(itemRequestDTO, id));
    }

}
