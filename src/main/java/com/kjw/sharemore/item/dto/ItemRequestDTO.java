package com.kjw.sharemore.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequestDTO {

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String category;

    @PositiveOrZero
    private int price;

    private String itemImage;

}
