package com.kjw.sharemore.item.controller;

import com.kjw.sharemore.apiPayLoad.ApiResponse;
import com.kjw.sharemore.item.dto.ItemRequestDTO;
import com.kjw.sharemore.item.dto.ItemResponseDTO;
import com.kjw.sharemore.item.service.ItemService;
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

    @GetMapping
    public ApiResponse<List<ItemResponseDTO>> getItemList() {
        return ApiResponse.onSuccess(itemService.getItemList());
    }

    @PostMapping
    public ApiResponse<ItemResponseDTO> postItem(@RequestBody ItemRequestDTO itemRequestDTO) {
        System.out.println("itemRequestDTO = " + itemRequestDTO);
        return ApiResponse.onSuccess(itemService.addItem(itemRequestDTO));
    }

}
