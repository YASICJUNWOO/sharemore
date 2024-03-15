package com.kjw.sharemore.domain.item.normalItem.repositoty;

import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String itemName);

    List<Item> findAllByCategoryAndPriceBetween(String category, int minPrice, int maxPrice);
    List<Item> findAllByPriceBetween(int minPrice, int maxPrice);

    Optional<Object> findAllByItemId(Long itemId);

    List<Item> findAllByOwner(Users user);

    List<Item> findAllByOrderByCreatedAtDesc();

    List<Item> findAllByOrderByLikeCountDesc();

    //List<Item> findAllByCategory(String category);
}
