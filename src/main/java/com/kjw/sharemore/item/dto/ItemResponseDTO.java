package com.kjw.sharemore.item.dto;

import com.kjw.sharemore.reservation.dto.ReservationItemDTO;
import com.kjw.sharemore.reservation.dto.ReservationResponseDTO;
import com.kjw.sharemore.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemResponseDTO {

    private Long id;

    private UserResponseDTO user;

    private String name;

    private String description;

    private String category;

    private int price;

    private String itemImage;

    //private List<ReviewResponseDTO> reviewList = new ArrayList<>();

    private List<ReservationItemDTO> reservationList = new ArrayList<>();

}
