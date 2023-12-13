package com.kjw.sharemore.reivew.dto;

import com.kjw.sharemore.users.dto.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ReviewResponseDTO {

    private UserResponseDTO reviewee;

    private UserResponseDTO reviewer;

    //private ItemResponseDTO item;

    private String comment;

    private int rating;

}
