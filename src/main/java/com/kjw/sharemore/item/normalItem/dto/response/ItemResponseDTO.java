package com.kjw.sharemore.item.dto.response;

import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.reservation.dto.response.ReservationItemDTO;
import com.kjw.sharemore.users.dto.UserSimpleResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 아이템 조회 응답 DTO
 **/
@Getter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class ItemResponseDTO extends ItemResponseBaseDTO {

    @Builder.Default
    private List<ReservationItemDTO> reservationList = new ArrayList<>();

    public static ItemResponseDTO of(Item item) {
        return ItemResponseDTO.builder()
                .id(item.getItemId())
                .name(item.getName())
                .description(item.getDescription())
                .category(item.getCategory())
                .price(item.getPrice())
                .itemImage(item.getItemImage())
                .reservationList(ReservationItemDTO.createList(item.getReservationList()))
                .owner(UserSimpleResponseDTO.of(item.getOwner()))
                .build();
    }

}