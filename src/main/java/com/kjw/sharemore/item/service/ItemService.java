package com.kjw.sharemore.item.service;

import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.item.converter.ItemConverter;
import com.kjw.sharemore.item.dto.ItemRequestDTO;
import com.kjw.sharemore.item.dto.ItemResponseDTO;
import com.kjw.sharemore.item.repositoty.ItemRepository;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;

    public List<ItemResponseDTO> getItemList() {
        return itemRepository.findAll().stream().map(
                ItemConverter::toDTO
        ).toList();
    }

    public ItemResponseDTO addItem(ItemRequestDTO itemRequestDTO) {
        Users userByEmail = userService.getUserByEmail(itemRequestDTO.getUserEmail()); //email로 유저 찾기
        log.info("userByEmail: {}", userByEmail);
        Item savedItem = itemRepository.save(ItemConverter.toEntity(itemRequestDTO,userByEmail)); //저장된 item
        return ItemConverter.toDTO(savedItem);
    }

    public Item getItemByName(String itemName) {
        log.info("itemName: {}", itemName);
        return itemRepository.findByName(itemName);
    }

    /**
    * @methodName : getItemByCategory
    * @param : category (카테고리)
    * @return : List<ItemResponseDTO>
    * @Description: 카테고리 별 아이템 조회
    **/
    public List<ItemResponseDTO> getItemByCategory(String category) {
        String decodedCategory = decode(category);
        return itemRepository.findAllByCategory(decodedCategory).stream().map(
                ItemConverter::toDTO
        ).toList();
    }

    /**
    * @methodName : decode
    * @Description: 한글 디코딩
    **/
    private String decode(String str) {
        return URLDecoder.decode(str, StandardCharsets.UTF_8);
    }
}
