package com.kjw.sharemore.users.converter;

import com.kjw.sharemore.item.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.reivew.dto.ReviewUserGetResponseDTO;
import com.kjw.sharemore.reivew.dto.ReviewUserPostResponseDTO;
import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.reservation.dto.response.ReservationUserResponseDTO;
import com.kjw.sharemore.users.dto.UserDetailResponseDTO;
import com.kjw.sharemore.users.dto.UserResponseDTO;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.dto.UserRequestDTO;
import lombok.Builder;

import java.util.List;

@Builder
public class UserConverter {

    public static Users toEntity(UserRequestDTO userRequestDTO) {
        return Users.builder()
                .name(userRequestDTO.getName())
                .email(userRequestDTO.getEmail())
                .password(userRequestDTO.getPassword())
                .address(userRequestDTO.getAddress())
                .build();
    }

    public static UserDetailResponseDTO toUserDetailResponseDTO(Users users, List<Review> getReviewList) {
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

    public static UserResponseDTO toUserResponseDTO(Users users) {
        return UserResponseDTO.builder()
                .name(users.getName())
                .email(users.getEmail())
                .password(users.getPassword())
                .address(users.getAddress())
                .build();
    }
    

}
