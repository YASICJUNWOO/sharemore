package com.kjw.sharemore.item.normalItem.service;

import com.kjw.sharemore.item.normalItem.repositoty.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemQueryService {

    private final ItemRepository itemRepository;

}
