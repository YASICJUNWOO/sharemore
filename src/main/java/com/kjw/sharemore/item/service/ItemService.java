package com.kjw.sharemore.item.service;

import com.kjw.sharemore.item.dto.request.ItemRequestDTO;
import com.kjw.sharemore.item.dto.response.ItemResponseDTO;
import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.item.repositoty.ItemRepository;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;
    private final UserService userService;

    public List<ItemResponseDTO> getItemList() {
        return itemRepository.findAll().stream().map(
                ItemResponseDTO::of
        ).toList();
    }

    public ItemResponseDTO addItem(ItemRequestDTO itemRequestDTO, Users user) {
        Users userByEmail = userService.getUserByEmail(user.getEmail()); //email로 유저 찾기
        Item savedItem = itemRepository.save(ItemRequestDTO.toEntity(itemRequestDTO, userByEmail)); //저장된 item
        return ItemResponseDTO.of(savedItem);
    }

    public Item getItemByName(String itemName) {
        log.info("itemName: {}", itemName);
        return itemRepository.findByName(itemName);
    }

    //아이템 조회해서 DTO로 변환
    public ItemResponseDTO getItemResponseById(Long itemId) {
        return ItemResponseDTO.of(getItemByItemId(itemId));
    }

    //아이템 조회
    public Item getItemByItemId(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow();
    }

    public ItemResponseDTO updateItem(ItemRequestDTO itemRequestDTO, Long itemId) {
        Item item = getItemByItemId(itemId);
        Item update = item.update(itemRequestDTO);
        return ItemResponseDTO.of(itemRepository.save(update));
    }

    /**
     * @methodName : getItemByCategory
     * @param : category (카테고리)
     * @return : List<ItemResponseDTO>
     * @Description: 카테고리 별 아이템 조회
     **/
    /*public List<ItemResponseDTO> getItemByCategory(String category) {
        String decodedCategory = decode(category);
        return itemRepository.findAllByCategory(decodedCategory).stream().map(
                ItemConverter::toDTO
        ).toList();
    }*/

    /**
     * @methodName : decode
     * @Description: 한글 디코딩
     **/
    /*private String decode(String str) {
        return URLDecoder.decode(str, StandardCharsets.UTF_8);
    }*/
}
