package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
    boolean existsByOrder(ProductOrder order);
    List<Review> findByProduct(Product product);
    List<Review> findByUserAndProduct(User user, Product product);

    @Query(value = "SELECT avg(productScore) FROM Review where product=:product")
    public Byte avgByProductScore(@Param("product") Product product);

    @Query(value = "SELECT avg(deliveryScore) FROM Review where product=:product")
    public Byte avgByDeliveryScore(@Param("product") Product product);
}
