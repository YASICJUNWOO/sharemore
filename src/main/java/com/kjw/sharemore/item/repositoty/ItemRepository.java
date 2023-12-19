package com.kjw.sharemore.item.repositoty;

import com.kjw.sharemore.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {
    Item findByName(String itemName);
}
