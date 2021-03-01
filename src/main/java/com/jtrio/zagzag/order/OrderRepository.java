package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface OrderRepository extends JpaRepository<ProductOrder, Long> {
    Page<ProductOrder> findByCreatedGreaterThanAndUser(LocalDateTime start, User user, Pageable pageable);

    boolean existsByUserAndProduct(User user, Product product);
}
