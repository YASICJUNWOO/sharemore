package com.kjw.sharemore.item.repositoty;

import com.kjw.sharemore.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String itemName);

    List<Item> findAllByCategoryAndPriceBetween(String category, int minPrice, int maxPrice);
    List<Item> findAllByPriceBetween(int minPrice, int maxPrice);

    //List<Item> findAllByCategory(String category);
}
