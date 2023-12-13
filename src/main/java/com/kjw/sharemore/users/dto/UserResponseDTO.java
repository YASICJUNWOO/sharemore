package com.kjw.sharemore.users.dto;

import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.reservation.Reservation;
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
public class UserResponseDTO {

    private String name;
    private String email;
    private String password;
    private String address;

}
