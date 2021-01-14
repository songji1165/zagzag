package com.jtrio.zagzag.liker;

import com.jtrio.zagzag.model.Liker;
import com.jtrio.zagzag.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikerRepository extends JpaRepository<Liker, Long> {
    Long countByUserIdAndReview(Long id, Review review);
    boolean existsByUserIdAndReview(Long id, Review review);
}
