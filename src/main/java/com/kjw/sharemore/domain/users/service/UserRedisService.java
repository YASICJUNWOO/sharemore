package com.kjw.sharemore.domain.users.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserRedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public Long addViewCount(String targetUserId, String viewerId) {
        if (!validateViewDuplicate(targetUserId, viewerId)) {
            addViewUser(targetUserId, viewerId);
            redisTemplate.opsForValue().increment("viewCount:" + targetUserId);
            return getViewCount(targetUserId);
        }
        return getViewCount(targetUserId);
    }

    public void addViewUser(String targetUserId, String viewerId) {
        redisTemplate.opsForSet().add("viewUser:" + targetUserId, viewerId);
    }

    // 이미 조회한 사용자인지 확인
    public Boolean validateViewDuplicate(String targetUserId, String viewerId) {
        return redisTemplate.opsForSet().isMember("viewUser:" + targetUserId, viewerId);
    }

    public Long getViewCount(String userId) {

        Object viewCount = redisTemplate.opsForValue().get("viewCount:" + userId);

        if (viewCount == null) {
            return 0L;
        }
        return Long.parseLong(viewCount.toString());

    }

}
