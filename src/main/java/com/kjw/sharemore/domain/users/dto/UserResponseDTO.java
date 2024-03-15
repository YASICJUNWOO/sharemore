package com.kjw.sharemore.domain.users.dto;

import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.reivew.entity.Review;
import com.kjw.sharemore.domain.reservation.dto.response.ReservationResponseDTO;
import com.kjw.sharemore.domain.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@SuperBuilder
public class UserResponseDTO {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Simple {

        private Long userId;
        private String name;
        private String email;
        private String address;

        public static UserResponseDTO.Simple of(Users user) {
            return UserResponseDTO.Simple.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .build();
        }

    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Detail {

        private Long userId;
        private String name;
        private String email;
        private String address;
        private List<Item> itemList;
        private List<Review> postReviewList;
        private List<ReservationResponseDTO.Detail> reservationList;
        private Long viewCount;

        public static UserResponseDTO.Detail of(Users user, Long viewCount) {
            return UserResponseDTO.Detail.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .itemList(user.getItemList())
                    .postReviewList(user.getPostReviewList())
                    .reservationList(user.getReservationList().stream().map(ReservationResponseDTO.Detail::of).toList())
                    .viewCount(viewCount)
                    .build();
        }

    }

}
