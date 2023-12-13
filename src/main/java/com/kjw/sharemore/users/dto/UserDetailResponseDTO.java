package com.kjw.sharemore.users.dto;

import com.kjw.sharemore.reivew.dto.ReviewResponseDTO;
import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.reservation.Reservation;
import com.kjw.sharemore.reservation.dto.ReservationResponseDTO;
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

    private List<ReservationResponseDTO> reservationList = new ArrayList<>();
    private List<ReviewResponseDTO> reviewList = new ArrayList<>();

}
