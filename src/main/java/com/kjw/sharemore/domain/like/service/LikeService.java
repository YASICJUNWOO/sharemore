package com.kjw.sharemore.domain.like.service;

import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.item.normalItem.repositoty.ItemRepository;
import com.kjw.sharemore.domain.like.repository.LikeRepository;
import com.kjw.sharemore.domain.like.entity.Likes;
import com.kjw.sharemore.domain.users.entity.Users;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LikeService {

    private final LikeRepository likeRepository;
    private final ItemRepository itemRepository;
    private final LikeQueryService likeQueryService;

    @Transactional
    public Integer addLike(Users users, Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다."));

        int likeCount = item.getLikeCount();

        if (likeQueryService.isLike(users.getUserId(), itemId)) {
            return updateLike(users, item);
        }

        Likes save = likeRepository.save(Likes.of(users, item));
        return item.addLikeCount();
    }

    @Transactional
    public int updateLike(Users users, Item item) {
        Likes likes = likeQueryService.findByUserAndItem(users.getUserId(), item.getItemId());

        int likeCount;
        if (likes.isState()){
            likeCount = item.subtractLikeCount();
        }
        else{
            likeCount = item.addLikeCount();
        }
        likes.update();
        return likeCount;
    }


}
