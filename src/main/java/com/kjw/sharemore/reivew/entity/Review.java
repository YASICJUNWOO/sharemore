package com.kjw.sharemore.reivew.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.kjw.sharemore.global.BaseEntity;
import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.users.entity.Users;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
/*
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewee_id", nullable = false)
    private Users reviewee;*/

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private Users reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id", nullable = false)
    private Item item;

    @Column(nullable = true)
    private String comment;

    @Column(nullable = false)
    private int rating;
}
