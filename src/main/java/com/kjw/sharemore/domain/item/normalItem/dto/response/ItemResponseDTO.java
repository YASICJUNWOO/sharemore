package com.kjw.sharemore.domain.item.normalItem.dto.response;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.domain.reservation.dto.response.ReservationResponseDTO;
import com.kjw.sharemore.domain.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class ItemResponseDTO {

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Simple {
        private Long itemId;
        private String name;
        private UserResponseDTO.Simple owner;
        private String description;
        private String category;
        private int price;
        private String itemImage;
        private Integer likeCount;

        public static ItemResponseDTO.Simple of(Item item){
            return Simple.builder()
                    .itemId(item.getItemId())
                    .name(item.getName())
                    .owner(UserResponseDTO.Simple.of(item.getOwner()))
                    .description(item.getDescription())
                    .category(item.getCategory())
                    .price(item.getPrice())
                    .itemImage(item.getItemImage())
                    .likeCount(item.getLikeCount())
                    .build();
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Detail {
        private Long itemId;
        private UserResponseDTO.Simple owner;
        private String name;
        private String description;
        private String category;
        private int price;
        private String itemImage;
        private Integer likeCount;
        private List<ReservationResponseDTO.Simple> reservationList;
        private List<ReviewResponseDTO.Simple> reviewList;

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        private LocalDateTime createdAt;

        private boolean isLike;
        private Long viewCount;

        public static ItemResponseDTO.Detail of(Item item, boolean isLike, Long viewCount){
            return Detail.builder()
                    .itemId(item.getItemId())
                    .owner(UserResponseDTO.Simple.of(item.getOwner()))
                    .name(item.getName())
                    .description(item.getDescription())
                    .category(item.getCategory())
                    .price(item.getPrice())
                    .itemImage(item.getItemImage())
                    .createdAt(item.getCreatedAt())
                    .likeCount(item.getLikeCount())
                    .reservationList(item.getReservationList().stream().map(ReservationResponseDTO.Simple::of).toList())
                    .reviewList(item.getReviewList().stream().map(ReviewResponseDTO.Simple::of).toList())
                    .viewCount(viewCount)
                    .build();
        }

    }
}
