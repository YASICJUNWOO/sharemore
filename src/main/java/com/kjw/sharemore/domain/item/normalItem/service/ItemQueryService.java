package com.kjw.sharemore.domain.item.normalItem.service;

import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.item.normalItem.repositoty.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemQueryService {

    private final ItemRepository itemRepository;

    public List<Item> getItemList() {
        return itemRepository.findAll();
    }

    public Item getItemByItemId(Long itemId) {
        return itemRepository.findById(itemId).orElseThrow(()->new IllegalArgumentException("해당 아이템이 존재하지 않습니다."));
    }

}
