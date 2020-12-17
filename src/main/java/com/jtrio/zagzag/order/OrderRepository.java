package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
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
    List<ProductOrder> findByCreatedBetweenAndUser(LocalDateTime start, LocalDateTime end,User user);
    List<ProductOrder> findAll();
    List<ProductOrder> findByUser(User user);
    List<ProductOrder> findByUserAndProduct(User user, Product product);
}
