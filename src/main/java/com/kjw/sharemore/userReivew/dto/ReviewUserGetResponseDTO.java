package com.kjw.sharemore.userReivew.dto;

import com.kjw.sharemore.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewUserGetResponseDTO {

    private UserResponseDTO reviewer;

    private String comment;

    private int rating;

}
