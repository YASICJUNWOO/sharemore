package com.kjw.sharemore.item.dto;

import com.kjw.sharemore.users.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemRequestDTO {

    //private Users user;
    private String userEmail;

    private String name;

    private String description;

    private String category;

    private int price;

}
