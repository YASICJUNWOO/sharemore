package com.kjw.sharemore.users.dto;

import com.kjw.sharemore.item.dto.ItemUserResponseDTO;
import com.kjw.sharemore.userReivew.dto.ReviewUserGetResponseDTO;
import com.kjw.sharemore.userReivew.dto.ReviewUserPostResponseDTO;
import com.kjw.sharemore.reservation.dto.ReservationUserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserDetailResponseDTO extends UserResponseDTO {

    private List<ItemUserResponseDTO> itemList = new ArrayList<>();
    private List<ReservationUserResponseDTO> reservationList = new ArrayList<>();
    private List<ReviewUserGetResponseDTO> getReviewList = new ArrayList<>();
    private List<ReviewUserPostResponseDTO> postReviewList = new ArrayList<>();

}
