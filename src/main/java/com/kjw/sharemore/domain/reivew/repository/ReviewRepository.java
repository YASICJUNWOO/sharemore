package com.kjw.sharemore.domain.reivew.repository;

import com.kjw.sharemore.domain.reivew.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}
