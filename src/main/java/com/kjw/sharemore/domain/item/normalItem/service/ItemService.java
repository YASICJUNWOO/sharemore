package com.kjw.sharemore.domain.item.normalItem.service;

import com.kjw.sharemore.domain.item.normalItem.dto.request.ItemRequestDTO;
import com.kjw.sharemore.domain.item.normalItem.dto.response.ItemResponseDTO;
import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.item.normalItem.entity.ItemDocument;
import com.kjw.sharemore.domain.item.normalItem.repositoty.ItemRepository;
import com.kjw.sharemore.domain.item.recentItem.ItemRedisService;
import com.kjw.sharemore.domain.like.entity.Likes;
import com.kjw.sharemore.domain.like.service.LikeQueryService;
import com.kjw.sharemore.domain.like.service.LikeService;
import com.kjw.sharemore.domain.users.entity.Users;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemSearchService itemSearchService;
    private final LikeService likeService;
    private final ItemRedisService itemRedisService;
    private final LikeQueryService likeQueryService;
    private final ItemQueryService itemQueryService;

    public List<ItemResponseDTO.Detail> getItemList() {
        List<Item> itemList = itemQueryService.getItemList();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return itemList.stream().map(
                item -> convertToDetail(item, principal)).toList();
    }

    //아이템 리스트 jwt 유무에 따라 다르게 반환
    public ItemResponseDTO.Detail convertToDetail(Item item, Object principal) {

        if (principal == null || principal.equals("anonymousUser")) {

            Long viewCount = itemRedisService.getViewCount(item.getItemId().toString());
            return ItemResponseDTO.Detail.of(item, false, viewCount);
        }

        Users user = (Users) principal;
        itemRedisService.saveRecentItem(item.getItemId(), user);
        Long view = itemRedisService.getViewCount(item.getItemId().toString());
        boolean isLike = Optional.ofNullable(likeQueryService.findByUserAndItem(user.getUserId(), item.getItemId()))
                .map(Likes::isState).orElse(false);

        return ItemResponseDTO.Detail.of(item, isLike, view);
    }

    public ItemResponseDTO.Simple addItem(ItemRequestDTO itemRequestDTO, Users user) {
        Item savedItem = itemRepository.save(ItemRequestDTO.toEntity(itemRequestDTO, user)); //저장된 item
        return ItemResponseDTO.Simple.of(savedItem);
    }

    //아이템 id로 아이템 조회
    public ItemResponseDTO.Detail getItemById(Long itemId) {
        Item item = itemQueryService.getItemByItemId(itemId);
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!principal.equals("anonymousUser")) {
            Users user = (Users) principal;
            Long view = itemRedisService.addViewCount(item.getItemId().toString(), user.getUserId().toString());
        }

        return convertToDetail(item, principal);
    }

    public ItemResponseDTO.Simple updateItem(ItemRequestDTO itemRequestDTO, Long itemId) {
        Item item = itemQueryService.getItemByItemId(itemId);
        Item update = item.update(itemRequestDTO);
        return ItemResponseDTO.Simple.of(itemRepository.save(update));
    }

    //아이템 필터링
    public List<ItemResponseDTO.Detail> getItemListByFilter(String keyword, String sortType) {

        List<Item> itemList = searchItems(keyword);
        itemList = sortList(sortType, itemList);

        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<ItemResponseDTO.Detail> list = itemList.stream().map(
                item -> convertToDetail(item, principal)).toList();
        log.info("{}", list.size());
        return list;
    }

    //아이템 검색
    private List<Item> searchItems(String keyword) {

        if (!keyword.isEmpty()){
            List<ItemDocument> itemByName = itemSearchService.getItemByName(keyword);
            return toItemList(itemByName);
        }

        return itemQueryService.getItemList();
    }

    //ItemDocument 리스트를 Item 리스트로 변환
    public List<Item> toItemList(List<ItemDocument> itemDocumentList) {
        return itemDocumentList.stream().map(
                itemDocument -> itemQueryService.getItemByItemId(Long.parseLong(itemDocument.getId()))
        ).toList();
    }

    private List<Item> sortList(String sortType, List<Item> itemList) {

        List<Item> sortedList = new ArrayList<>();

        //좋아요 순으로 정렬
        if (sortType.equals("likeDesc")) {
            sortedList = itemList.stream().sorted((o1, o2) -> o2.getLikeCount().compareTo(o1.getLikeCount())).toList();
        }
        //최신순으로 정렬
        else if (sortType.equals("recentDesc") || sortType.isEmpty()) {
            log.info("recentDesc");
            sortedList = itemList.stream().sorted((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt())).toList();
        }
        else if (sortType.equals("lookDesc")){
            sortedList = itemList.stream().sorted((o1, o2) -> {
                Long viewCount1 = itemRedisService.getViewCount(o1.getItemId().toString());
                Long viewCount2 = itemRedisService.getViewCount(o2.getItemId().toString());
                return viewCount2.compareTo(viewCount1);
            }).toList();
        }

        return sortedList;
    }

}
