package com.jtrio.zagzag.model;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.product.ProductDto.CreateProductDto;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(value = {AuditingEntityListener.class})
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;
    private String image;
    private byte productScore;
    private byte deliveryScore;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private LocalDateTime created;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime updated;

//    public CreateProductDto toProductDto(){
//        CreateProductDto productDto= new CreateProductDto();
//
//        productDto.setName(name);
//        productDto.setPrice(price);
//        productDto.setImage(image);
//        productDto.setProductScore(productScore);
//        productDto.setDeliveryScore(deliveryScore);
//        productDto.setCategory(category);
//
//        return productDto;
//    }

}
