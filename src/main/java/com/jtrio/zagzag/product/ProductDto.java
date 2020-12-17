package com.jtrio.zagzag.product;

import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.model.Category;
import lombok.Data;

@Data
public class ProductDto {

    @Data
    public static class CreateProductDto {
        private String name;
        private Integer price;
        private String image;
        private Byte productScore;
        private Byte deliveryScore;
        private Category category;
    }

    @Data
    public static class OrderProduct{
        private Long id;
        private String name;
        private Integer price;
        private String image;
        private Category category;
    }


}
