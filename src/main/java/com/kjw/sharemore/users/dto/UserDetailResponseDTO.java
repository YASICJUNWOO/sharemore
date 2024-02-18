package com.kjw.sharemore.users.dto;

import com.kjw.sharemore.item.dto.response.ItemResponseBaseDTO;
import com.kjw.sharemore.reservation.dto.response.ReservationUserResponseDTO;
import com.kjw.sharemore.userReivew.dto.ReviewUserGetResponseDTO;
import com.kjw.sharemore.userReivew.dto.ReviewUserPostResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
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

    @Builder.Default
    private List<ItemResponseBaseDTO> itemList = new ArrayList<>();

    @Builder.Default
    private List<ReservationUserResponseDTO> reservationList = new ArrayList<>();

    @Builder.Default
    private List<ReviewUserGetResponseDTO> getReviewList = new ArrayList<>();

    @Builder.Default
    private List<ReviewUserPostResponseDTO> postReviewList = new ArrayList<>();

}
