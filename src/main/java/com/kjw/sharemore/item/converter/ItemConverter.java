package com.kjw.sharemore.item.converter;

import com.kjw.sharemore.item.dto.ItemReservationResponseDTO;
import com.kjw.sharemore.item.dto.ItemUserResponseDTO;
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
                .itemImage(itemRequestDTO.getItemImage())
                .build();
    }

    public static ItemResponseDTO toDTO(Item item) {
        return ItemResponseDTO.builder()
                .id(item.getItemId())
                .user(UserConverter.toUserResponseDTO(item.getUser()))
                .name(item.getName())
                .description(item.getDescription())
                .category(item.getCategory())
                .price(item.getPrice())
                .reservationList(item.getReservationList().stream().map(ReservationConverter::toReservationItemDTO).toList())
                .itemImage(item.getItemImage())
                .build();
    }

    public static ItemUserResponseDTO toItemUserDTO(Item item) {
        return ItemUserResponseDTO.builder()
                .name(item.getName())
                .description(item.getDescription())
                .category(item.getCategory())
                .price(item.getPrice())
                .itemImage(item.getItemImage())
                .build();
    }

    public static ItemReservationResponseDTO toItemReservationDTO(Item item) {
        return ItemReservationResponseDTO.builder()
                .owner(UserConverter.toUserResponseDTO(item.getUser()))
                .name(item.getName())
                .description(item.getDescription())
                .category(item.getCategory())
                .price(item.getPrice())
                .itemImage(item.getItemImage())
                .build();
    }

    public static <T> List<T> validate(List<T> t){
        return Optional.ofNullable(t).orElseGet(Collections::emptyList);
    }

}
