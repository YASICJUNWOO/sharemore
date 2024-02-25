package com.kjw.sharemore.reivew.repository;

import com.kjw.sharemore.reivew.entity.Review;
import com.kjw.sharemore.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByItem_Owner(Users user);

    List<Review> findAllByReviewer(Users user);
}
