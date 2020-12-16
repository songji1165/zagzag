package com.jtrio.zagzag.product;

import com.jtrio.zagzag.category.CategoryRepository;
import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class ProductCommand {

    @Data
    public static class CreateProduct {
        @NotBlank
        private String name;
        @NotBlank
        private Integer price;
        private String image;
        private Byte productScore;
        private Byte deliveryScore;
        private Long categoryId;

        public Product toProduct(Category category){
            Product product = new Product();

            product.setName(name);
            product.setPrice(price);
            product.setImage(image);
            product.setProductScore(productScore);
            product.setDeliveryScore(deliveryScore);
            product.setCategory(category);

            return product;
        }
    }

    @Data
    public static class SelectProduct {
        @NotBlank
        private String name;
        @NotBlank
        private Integer price;

    }

}
