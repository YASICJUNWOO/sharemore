package com.kjw.sharemore.reivew.dto;

import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.users.entity.Users;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ReviewRequestDTO {

    private String revieweeEmail;
    //private Users reviewee;

    private String reviewerEmail;
    //private Users reviewer;

    //private Item item;

    private String comment;

    private int rating;
}
