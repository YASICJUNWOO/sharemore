package com.kjw.sharemore.users.dto;

import com.kjw.sharemore.item.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.reservation.dto.response.ReservationUserResponseDTO;
import com.kjw.sharemore.reivew.dto.ReviewUserGetResponseDTO;
import com.kjw.sharemore.reivew.dto.ReviewUserPostResponseDTO;
import com.kjw.sharemore.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponseDTO extends UserResponseDTO {

    @Builder.Default
    private List<ItemResponseBaseDTO> itemList = new ArrayList<>();

    @Builder.Default
    private List<ReservationUserResponseDTO> reservationList = new ArrayList<>();

    @Builder.Default
    private List<ReviewResponseDTO> getReviewList = new ArrayList<>();

    @Builder.Default
    private List<ReviewUserPostResponseDTO> postReviewList = new ArrayList<>();

    public static UserDetailResponseDTO of(Users users, List<Review> getReviewList) {
        return UserDetailResponseDTO.builder()
                .name(users.getName())
                .email(users.getEmail())
                .password(users.getPassword())
                .address(users.getAddress())
                .itemList(users.getItemList().stream().map(ItemResponseBaseDTO::of).toList())
                .getReviewList(getReviewList.stream().map(ReviewResponseDTO::of).toList())
                .postReviewList(users.getPostReviewList().stream().map(ReviewUserPostResponseDTO::of).toList())
                .reservationList(ReservationUserResponseDTO.createList(users.getReservationList()))
                .build();
    }

}
