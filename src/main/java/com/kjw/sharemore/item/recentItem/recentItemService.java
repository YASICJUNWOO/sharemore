package com.kjw.sharemore.item.recentItem;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class recentItemService {

    private final com.kjw.sharemore.item.repositoty.ItemRepository itemRepository;


    public void saveRecentItem(Long itemId) {

    }
}
