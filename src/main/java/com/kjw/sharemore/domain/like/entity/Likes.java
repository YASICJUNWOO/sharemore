package com.kjw.sharemore.domain.like.entity;

import com.kjw.sharemore.global.BaseEntity;
import com.kjw.sharemore.domain.item.normalItem.entity.Item;
import com.kjw.sharemore.domain.users.entity.Users;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Likes extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    private boolean state;

    @ManyToOne(fetch = FetchType.LAZY)
    private Users user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Item item;

    public Likes update() {
        this.state = !this.state;
        return this;
    }

    public static Likes of(Users user, Item item) {
        return Likes.builder()
                .state(true)
                .user(user)
                .item(item)
                .build();
    }

}
