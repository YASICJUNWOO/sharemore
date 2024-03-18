package com.kjw.sharemore.domain.users.dto;

import com.kjw.sharemore.domain.item.normalItem.dto.response.ItemResponseDTO;
import com.kjw.sharemore.domain.reivew.dto.ReviewResponseDTO;
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
        private int point;

        public static UserResponseDTO.Simple of(Users user) {
            return UserResponseDTO.Simple.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .point(user.getPoint())
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
        private int point;
        private List<ItemResponseDTO.Simple> itemList;
        private List<ReviewResponseDTO.Detail> postReviewList;
        private List<ReservationResponseDTO.Detail> reservationList;
        private Long viewCount;

        public static UserResponseDTO.Detail of(Users user, Long viewCount) {
            return UserResponseDTO.Detail.builder()
                    .userId(user.getUserId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .address(user.getAddress())
                    .point(user.getPoint())
                    .itemList(user.getItemList().stream().map(ItemResponseDTO.Simple::of).toList())
                    .postReviewList(user.getPostReviewList().stream().map(ReviewResponseDTO.Detail::of).toList())
                    .reservationList(user.getReservationList().stream().map(ReservationResponseDTO.Detail::of).toList())
                    .viewCount(viewCount)
                    .build();
        }

    }

}
