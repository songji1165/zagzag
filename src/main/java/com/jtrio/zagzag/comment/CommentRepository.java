package com.jtrio.zagzag.comment;

import com.jtrio.zagzag.model.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findById(Long id);
//    Page<Review> findByProduct(Product product, Pageable pageable);
    List<Comment> findByQuestion(Question question);
    Long countByQuestion(Question question);
}
