package com.kjw.sharemore.item.normalItem.service;

import com.kjw.sharemore.item.normalItem.entity.ItemDocument;
import com.kjw.sharemore.item.normalItem.repositoty.ItemSearchRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemSearchService {

    private final ItemSearchRepository itemSearchRepository;

    public ItemDocument createItem(ItemDocument itemDocument) {
        return itemSearchRepository.save(itemDocument);
    }

    public List<ItemDocument> getItemByName(String keyword) {
        List<ItemDocument> byName = itemSearchRepository.findByName(keyword);
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
