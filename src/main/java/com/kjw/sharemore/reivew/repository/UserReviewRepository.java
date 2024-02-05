package com.kjw.sharemore.reivew.repository;

import com.kjw.sharemore.reivew.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}
