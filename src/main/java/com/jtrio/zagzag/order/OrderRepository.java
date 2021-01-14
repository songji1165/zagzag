package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder, Long> {
///    (Category category, Pageable pageable);
    Page<ProductOrder> findByCreatedGreaterThanAndUser(LocalDateTime start, User user, Pageable pageable);
    boolean existsByUserAndProduct(User user, Product product);
}
