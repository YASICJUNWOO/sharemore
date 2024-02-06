package com.kjw.sharemore.item.repositoty;

import com.kjw.sharemore.item.entity.ItemDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface ItemSearchRepository extends ElasticsearchRepository<ItemDocument, Long> {

    List<ItemDocument> findByName(String keyword);

    List<ItemDocument> findByNameContainingOrDescriptionContaining(String keyword1, String keyword2);
}
