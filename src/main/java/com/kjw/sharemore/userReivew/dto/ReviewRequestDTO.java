package com.kjw.sharemore.userReivew.dto;

import lombok.Getter;

@Getter
public class ReviewRequestDTO {

    private String revieweeEmail;

    private String comment;

    private int rating;
}
