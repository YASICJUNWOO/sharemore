package com.kjw.sharemore.item.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.kjw.sharemore.global.BaseEntity;
import com.kjw.sharemore.item.dto.ItemRequestDTO;
import com.kjw.sharemore.reservation.Reservation;
import com.kjw.sharemore.users.entity.Users;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Users user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = true)
    private String description;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int price;

    @Column(nullable = true)
    private String itemImage;

    //@OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    //private List<Review> reviewList = new ArrayList<>();

    @JsonManagedReference
    @Builder.Default
    @OneToMany(mappedBy = "item", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    public Item update(ItemRequestDTO itemRequestDTO) {
        this.name = itemRequestDTO.getName();
        this.description = itemRequestDTO.getDescription();
        this.category = itemRequestDTO.getCategory();
        this.price = itemRequestDTO.getPrice();
        this.itemImage = itemRequestDTO.getItemImage();
        return this;
    }
}
