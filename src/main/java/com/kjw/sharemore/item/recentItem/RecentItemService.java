package com.kjw.sharemore.item.recentItem;

import com.kjw.sharemore.item.normalItem.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.item.normalItem.entity.Item;
import com.kjw.sharemore.item.normalItem.repositoty.ItemRepository;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
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

        long createdAt = LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
        log.info("createdAt : " + createdAt);

        String key = "recentItem" + memberName;
        String value = String.valueOf(itemId);
        redisTemplate.opsForZSet().add(key, value, createdAt);

        return value;
    }


    public List<ItemResponseBaseDTO> getRecentItem(Users user) {
        String memberName = user.getUsername();
        String key = "recentItem" + memberName;

        Set<Object> recentItems = redisTemplate.opsForZSet().reverseRange(key, 0, 4);

        List<ItemResponseBaseDTO> list = Objects.requireNonNull(recentItems).stream().map(r -> {
            Item item = itemRepository.findById(Long.parseLong((String) r)).get();
            return ItemResponseBaseDTO.of(item);
        }).toList();

        return list;
    }

    @Transactional
    public void saveHotKeyWord(String keyword) {
        String key = "hotKeyWord";
        redisTemplate.opsForZSet().incrementScore(key, keyword, 1);
    }

    public List<HotKeyWordDTO> getHotKeyWord() {
        String key = "hotKeyWord";
        Set<ZSetOperations.TypedTuple<Object>> typedTuples = redisTemplate.opsForZSet().reverseRangeWithScores(key, 0, 9);
        return Objects.requireNonNull(typedTuples).stream().map(
                t -> HotKeyWordDTO.of((String) t.getValue(), (int)Math.round(t.getScore()))
        ).toList();

    }

}
