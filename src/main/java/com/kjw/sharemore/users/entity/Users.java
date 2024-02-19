package com.kjw.sharemore.users.entity;

import com.kjw.sharemore.global.BaseEntity;
import com.kjw.sharemore.item.entity.Item;
import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.reservation.Reservation;
import com.kjw.sharemore.users.dto.UserRequestDTO;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@ToString
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Builder
public class Users extends BaseEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String address;

    @Builder.Default
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Item> itemList = new ArrayList<>();


    @Builder.Default
    @OneToMany(mappedBy = "reviewer", cascade = CascadeType.ALL)
    private List<Review> postReviewList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Reservation> reservationList = new ArrayList<>();

    public void update(UserRequestDTO userRequestDTO) {
        this.name = userRequestDTO.getName();
        this.email = userRequestDTO.getEmail();
        this.password = userRequestDTO.getPassword();
        this.address = userRequestDTO.getAddress();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
