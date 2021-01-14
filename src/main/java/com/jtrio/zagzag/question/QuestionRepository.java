package com.jtrio.zagzag.question;

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
public interface QuestionRepository extends JpaRepository<Question, Long> {
//    boolean existsByOrder(ProductOrder order);
    Optional<Question> findById(Long id);
    Page<Question> findByProduct(Product product, Pageable pageable);
}
