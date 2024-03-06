package com.kjw.sharemore.item.normalItem.service;

import com.kjw.sharemore.item.normalItem.dto.request.ItemRequestDTO;
import com.kjw.sharemore.item.normalItem.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.item.normalItem.dto.response.ItemResponseDTO;
import com.kjw.sharemore.item.normalItem.entity.Item;
import com.kjw.sharemore.item.normalItem.entity.ItemDocument;
import com.kjw.sharemore.item.normalItem.repositoty.ItemRepository;
import com.kjw.sharemore.like.LikeService;
import com.kjw.sharemore.users.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemSearchService itemSearchService;
    private final LikeService likeService;

    public List<ItemResponseDTO> getItemList() {
        List<Item> all = itemRepository.findAll();

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return all.stream().map(
                    ItemResponseDTO::of
            ).toList();
        }
        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return all.stream().map(
                item -> {
                    boolean isLike = likeService.isLike(user.getUserId(), item.getItemId());
                    return ItemResponseDTO.toDTOWithLike(item, isLike);
                }
        ).toList();
    }

    public ItemResponseDTO addItem(ItemRequestDTO itemRequestDTO, Users user) {
        Item savedItem = itemRepository.save(ItemRequestDTO.toEntity(itemRequestDTO, user)); //저장된 item
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

    public List<ItemResponseBaseDTO> getItemByOwner(Users user) {
        return itemRepository.findAllByOwner(user).stream().map(
                ItemResponseBaseDTO::of
        ).toList();
    }

    public List<ItemResponseDTO> getItemListByFilter(String keyword, String sortType) {

        List<Item> itemList = getItems(keyword);

        itemList = sortList(sortType, itemList);
        log.info("itemList: {}", itemList.size());

        if (SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return itemList.stream().map(
                    ItemResponseDTO::of
            ).toList();
        }

        Users user = (Users) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return itemList.stream().map(
                item -> {
                    boolean isLike = likeService.isLike(user.getUserId(), item.getItemId());
                    return ItemResponseDTO.toDTOWithLike(item, isLike);
                }
        ).toList();

    }

    private List<Item> getItems(String keyword) {

        if (!keyword.isEmpty()){
            log.info("keyword is empty");
            List<ItemDocument> itemByName = itemSearchService.getItemByName(keyword);
            return toItemList(itemByName);
        }

        return itemRepository.findAll();
    }

    public List<Item> toItemList(List<ItemDocument> itemDocumentList) {
        return itemDocumentList.stream().map(
                itemDocument -> itemRepository.findById(Long.parseLong(itemDocument.getId())).orElseThrow()
        ).toList();
    }

    private List<Item> sortList(String sortType, List<Item> itemList) {

        List<Item> sortedList = new ArrayList<>();

        log.info("sortType: {}", sortType);
        //좋아요 순으로 정렬
        if (sortType.equals("likeDesc")) {
            log.info("likeDesc");
            sortedList = itemList.stream().sorted((o1, o2) -> o2.getLikeCount().compareTo(o1.getLikeCount())).toList();
        }
        //최신순으로 정렬
        else if (sortType.equals("recentDesc") || sortType.isEmpty()) {
            sortedList = itemList.stream().sorted((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt())).toList();
        }

        return sortedList;
    }

}
