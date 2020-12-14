package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
//    List<Product> findAll();

    List<Product> findByCategory(Category category);

}
