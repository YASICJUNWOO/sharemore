package com.kjw.sharemore.item.normalItem.service;

import com.kjw.sharemore.item.normalItem.entity.ItemDocument;
import com.kjw.sharemore.item.normalItem.repositoty.ItemRepository;
import com.kjw.sharemore.item.normalItem.repositoty.ItemSearchRepository;
import com.kjw.sharemore.item.recentItem.ItemRedisService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemSearchService {

    private final ItemSearchRepository itemSearchRepository;
    private final ItemRepository itemRepository;
    private final ItemRedisService itemRedisService;

    public ItemDocument createItem(ItemDocument itemDocument) {
        return itemSearchRepository.save(itemDocument);
    }

    public List<ItemDocument> getItemByName(String keyword) {
        List<ItemDocument> byName = itemSearchRepository.findByName(keyword);
        itemRedisService.saveHotKeyWord(keyword);
        return byName;
    }

    public List<ItemDocument> getItemByNameAndDescription(String keyword) {
        List<ItemDocument> byName = itemSearchRepository.findByNameContainingOrDescriptionContaining(keyword, keyword);
        return byName;
    }

    public void deleteItemIndex() {
        itemSearchRepository.deleteAll();
    }

}
