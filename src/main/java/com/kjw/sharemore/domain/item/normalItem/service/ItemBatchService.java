package com.kjw.sharemore.domain.item.normalItem.service;

import com.kjw.sharemore.domain.item.normalItem.dto.response.ItemResponseDTO;
import com.kjw.sharemore.domain.item.recentItem.ItemRedisService;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemBatchService {

    private final ItemRedisService itemRedisService;
    private final ItemService itemService;

    @Getter
    public List<ItemResponseDTO.Detail> popularItems = new ArrayList<>();

    @Scheduled(cron = "0 0 0 * * *")
    public void clearItemList() {
        popularItems = new ArrayList<>();
    }

    @Transactional
    @Scheduled(cron = "0/5 * 0-23 * * *")
    public void updateItemStatus() {
        popularItems = itemRedisService.getDailyViewCountRank().stream()
                .map(item -> itemService.convertToDetail(item, null))
                .toList();
    }

}
