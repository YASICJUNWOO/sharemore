package com.kjw.sharemore.item.recentItem;

import com.kjw.sharemore.item.normalItem.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.item.normalItem.repositoty.ItemRepository;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecentItemService {

    private final ItemRepository itemRepository;
    private final UserRepository userRepository;
    private final RedisTemplate<String, Object> redisTemplate;


    public String saveRecentItem(Long itemId, Users user) {

        String memberName = user.getUsername();

        String itemName = itemRepository.findById(itemId).get().getName();
        long createdAt = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        log.info("createdAt : " + createdAt);

        String key = "recentItem" + memberName;
        redisTemplate.opsForZSet().add(key, itemName, createdAt);

        return itemName;
    }


    public List<ItemResponseBaseDTO> getRecentItem(Users user) {
        String memberName = user.getUsername();
        String key = "recentItem" + memberName;

        Set<Object> recentItems = redisTemplate.opsForZSet().reverseRange(key, 0, 4);

        List<ItemResponseBaseDTO> list = Objects.requireNonNull(recentItems).stream().map(r -> {
            return ItemResponseBaseDTO.of(itemRepository.findByName((String) r));
        }).toList();

        return list;
    }

}
