package com.kjw.sharemore.reivew.repository;

import com.kjw.sharemore.reivew.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
