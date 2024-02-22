package com.kjw.sharemore.item.service;

import com.kjw.sharemore.item.dto.response.ItemResponseDTO;
import com.kjw.sharemore.item.dto.search.SearchDTO;
import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.item.repositoty.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchService {

    private final ItemRepository itemRepository;

    /**
     * @param : searchDTO
     * @return : List<ItemResponseDTO>
     * @methodName : searchItem
     * @Description: 검색
     * @note: 카테고리별, 가격별 검색
     **/
    public List<ItemResponseDTO> searchItem(SearchDTO searchDTO) {

        List<Item> result = new ArrayList<>();

        if (searchDTO.getCategory().equals("전체")) {
            result = itemRepository.findAllByPriceBetween(searchDTO.getMinPrice(), searchDTO.getMaxPrice());
        } else {
            result = itemRepository.findAllByCategoryAndPriceBetween(searchDTO.getCategory(), searchDTO.getMinPrice(), searchDTO.getMaxPrice());
        }

        return result.stream().map(
                ItemResponseDTO::of
        ).toList();
    }
}
