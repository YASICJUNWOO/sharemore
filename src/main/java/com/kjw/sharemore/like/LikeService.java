package com.kjw.sharemore.like;

import com.kjw.sharemore.item.normalItem.entity.Item;
import com.kjw.sharemore.item.normalItem.repositoty.ItemRepository;
import com.kjw.sharemore.users.entity.Users;
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

    @Transactional
    public Integer addLike(Users users, Long itemId) {
        Item item = itemRepository.findById(itemId).orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다."));

        int likeCount = item.getLikeCount();

        if (isLike(users.getUserId(), itemId)) {
            return updateLike(users, item);
        }

        Likes likes = Likes.of(users, item);
        Likes save = likeRepository.save(likes);
        item.addLikeCount();
        return item.getLikeCount();
    }

    @Transactional
    public int updateLike(Users users, Item item) {
        Likes likes = likeRepository.findByUser_UserIdAndItem_ItemId(users.getUserId(), item.getItemId()).get();

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

    public boolean isLike(Long userId, Long itemId) {
        return likeRepository.existsByUser_UserIdAndItem_ItemId(userId, itemId);
    }

}
