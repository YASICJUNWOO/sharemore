package com.kjw.sharemore.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequestDTO {

    //private Users user;
    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$", message = "이메일 형식이 아닙니다.")
    private String userEmail;

    @NotBlank
    private String name;

    private String description;

    @NotBlank
    private String category;

    @PositiveOrZero
    private int price;

    private String itemImage;

}
