package com.kjw.sharemore.users.converter;

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
                .reviewList(users.getReviewList())
                .reservationList(users.getReservationList())
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
