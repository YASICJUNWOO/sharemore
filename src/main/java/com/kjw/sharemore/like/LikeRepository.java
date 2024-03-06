package com.kjw.sharemore.like;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeRepository extends JpaRepository<Likes, Long> {
    boolean existsByUser_UserIdAndItem_ItemId(Long userId, Long itemId);

    Optional<Likes> findByUser_UserIdAndItem_ItemId(Long userId, Long itemId);
}
