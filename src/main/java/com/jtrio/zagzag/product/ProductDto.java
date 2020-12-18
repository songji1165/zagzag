package com.jtrio.zagzag.product;

import com.jtrio.zagzag.enums.Gender;
import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import lombok.Data;

@Data
public class ProductDto {

    @Data
    public static class CreateProductDto {
        private String name;
        private Integer price;
        private String image;
        private byte productScore;
        private byte deliveryScore;
        private Category category;

        public static CreateProductDto toProductDto(Product product){
            CreateProductDto productDto= new CreateProductDto();

            productDto.setName(product.getName());
            productDto.setPrice(product.getPrice());
            productDto.setImage(product.getImage());
            productDto.setProductScore(product.getProductScore());
            productDto.setDeliveryScore(product.getDeliveryScore());
            productDto.setCategory(product.getCategory());

            return productDto;
        }
    }

    @Data
    public static class OrderProductDto{
        private Long id;
        private String name;
        private Integer price;
        private String image;
        private Category category;
    }


}
