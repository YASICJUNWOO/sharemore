package com.kjw.sharemore.domain.item.normalItem.dto.search;

import lombok.Getter;

@Getter
public class SearchDTO {

    private String category;
    private int minPrice;
    private int maxPrice;

}