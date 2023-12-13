package com.kjw.sharemore.item.converter;

import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.item.dto.ItemRequestDTO;
import com.kjw.sharemore.item.dto.ItemResponseDTO;
import com.kjw.sharemore.reservation.converter.ReservationConverter;
import com.kjw.sharemore.users.converter.UserConverter;
import com.kjw.sharemore.users.entity.Users;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Builder
@RequiredArgsConstructor
public class ItemConverter {

    public static Item toEntity(ItemRequestDTO itemRequestDTO, Users user) {
        return Item.builder()
                .user(user)
                .name(itemRequestDTO.getName())
                .description(itemRequestDTO.getDescription())
                .category(itemRequestDTO.getCategory())
                .price(itemRequestDTO.getPrice())
                .build();
    }

    public static ItemResponseDTO toDTO(Item item) {
        return ItemResponseDTO.builder()
                .user(UserConverter.toUserDetailResponseDTO(item.getUser()))
                .name(item.getName())
                .description(item.getDescription())
                .category(item.getCategory())
                .price(item.getPrice())
                //.reviewList(validate(item.getReviewList()).stream().map(ReviewConverter::toDTO).toList())
                .reservationList(validate(item.getReservationList()).stream().map(ReservationConverter::toDto).toList())
                .build();
    }

    public static <T> List<T> validate(List<T> t){
        return Optional.ofNullable(t).orElseGet(Collections::emptyList);
    }

}
