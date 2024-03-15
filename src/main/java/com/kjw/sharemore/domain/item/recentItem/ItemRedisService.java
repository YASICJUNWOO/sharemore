package com.kjw.sharemore.domain.item.recentItem;

import com.kjw.sharemore.domain.item.normalItem.dto.response.ItemResponseDTO;
import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.item.normalItem.repositoty.ItemRepository;
import com.kjw.sharemore.domain.users.entity.Users;
import com.kjw.sharemore.domain.users.repository.UserRepository;
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
public class ItemRedisService {

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


    public List<ItemResponseDTO.Simple> getRecentItem(Users user) {
        String memberName = user.getUsername();
        String key = "recentItem" + memberName;

        Set<Object> recentItems = redisTemplate.opsForZSet().reverseRange(key, 0, 4);

        List<ItemResponseDTO.Simple> list = Objects.requireNonNull(recentItems).stream().map(r -> {
            Item item = itemRepository.findById(Long.parseLong((String) r)).get();
            Long viewCount = getViewCount(item.getItemId().toString());
            return ItemResponseDTO.Simple.of(item);
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
                t -> {
                    String value = (String) t.getValue();
                    int round = (int) Math.round(t.getScore());
                    return HotKeyWordDTO.of(value, round);
                }
        ).toList();
    }

    public Long addViewCount(String itemId, String viewerId) {
        if (!validateViewDuplicate(itemId, viewerId)) {
            addViewUser(itemId, viewerId);
            redisTemplate.opsForValue().increment("viewCount:" + itemId);
            return getViewCount(itemId);
        }
        return getViewCount(itemId);
    }

    public void addViewUser(String itemId, String viewerId) {
        redisTemplate.opsForSet().add("viewUser:" + itemId, viewerId);
    }

    // 이미 조회한 사용자인지 확인
    public Boolean validateViewDuplicate(String itemId, String viewerId) {
        return redisTemplate.opsForSet().isMember("viewUser:" + itemId, viewerId);
    }

    public Long getViewCount(String itemId) {

        Object viewCount = redisTemplate.opsForValue().get("viewCount:" + itemId);

        if (viewCount == null) {
            return 0L;
        }
        return Long.parseLong(viewCount.toString());

    }

}
