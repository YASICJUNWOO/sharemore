package com.kjw.sharemore.domain.like.service;

import com.kjw.sharemore.domain.like.repository.LikeRepository;
import com.kjw.sharemore.domain.like.entity.Likes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LikeQueryService {

    private final LikeRepository likeRepository;

    public boolean isLike(Long userId, Long itemId) {
        return likeRepository.existsByUser_UserIdAndItem_ItemId(userId, itemId);
    }

    public Likes findByUserAndItem(Long userId, Long itemId) {
        return likeRepository.findByUser_UserIdAndItem_ItemId(userId, itemId).orElse(null);
    }

}
