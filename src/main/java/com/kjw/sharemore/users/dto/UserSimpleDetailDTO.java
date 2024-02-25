package com.kjw.sharemore.users.dto;

import com.kjw.sharemore.item.normalItem.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserSimpleDetailDTO extends UserResponseDTO {

    @Builder.Default
    private List<ItemResponseBaseDTO> itemList = new ArrayList<>();

    @Builder.Default
    private List<ReviewResponseDTO> getReviewList = new ArrayList<>();

    public static UserSimpleDetailDTO of(Users users,
                                         List<ItemResponseBaseDTO> itemList,
                                         List<ReviewResponseDTO> getReviewList) {
        return UserSimpleDetailDTO.builder()
                .name(users.getName())
                .email(users.getEmail())
                .password(users.getPassword())
                .address(users.getAddress())
                .itemList(itemList)
                .getReviewList(getReviewList)
                .build();
    }
}
