package com.kjw.sharemore.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemUserResponseDTO {

    //user 중복되어 삭제
    //reservationList 삭제

    private String name;

    private String description;

    private String category;

    private int price;

    private String itemImage;

}
