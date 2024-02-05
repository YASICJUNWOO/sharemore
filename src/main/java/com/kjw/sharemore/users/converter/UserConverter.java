package com.kjw.sharemore.users.converter;

import com.kjw.sharemore.item.converter.ItemConverter;
import com.kjw.sharemore.reivew.converter.UserReviewConverter;
import com.kjw.sharemore.reservation.converter.ReservationConverter;
import com.kjw.sharemore.users.dto.UserDetailResponseDTO;
import com.kjw.sharemore.users.dto.UserResponseDTO;
import com.kjw.sharemore.users.entity.Users;
import com.kjw.sharemore.users.dto.UserRequestDTO;
import lombok.Builder;

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

    public static UserDetailResponseDTO toUserDetailResponseDTO(Users users) {
        return UserDetailResponseDTO.builder()
                .name(users.getName())
                .email(users.getEmail())
                .password(users.getPassword())
                .address(users.getAddress())
                .itemList(users.getItemList().stream().map(ItemConverter::toItemUserDTO).toList())
                .getReviewList(users.getUserReviewList().stream().map(UserReviewConverter::toUserGetDTO).toList())
                .postReviewList(users.getPostUserReviewList().stream().map(UserReviewConverter::toUserPostDTO).toList())
                .reservationList(users.getReservationList().stream().map(ReservationConverter::toReservationUserResponseDTO).toList())
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
