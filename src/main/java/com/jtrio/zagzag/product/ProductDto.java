package com.jtrio.zagzag.product;

import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.model.Category;
import lombok.Data;

@Data
public class ProductDto {
    private String name;
    private Integer price;
    private String image;
    private Byte productScore;
    private Byte deliveryScore;
    private Long categoryId ;
    private String categoryName ;

}
