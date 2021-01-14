package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    Page<Product> findAllByCategory(Category category, Pageable pageable);
    Optional<Product> findById(Long id);
}
