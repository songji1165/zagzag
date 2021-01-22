package com.jtrio.zagzag.product;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class ProductCommand {

    @Data
    public static class CreateProduct {
        @NotBlank(message = "상품명을 입력해주세요.")
        private String name;
        @Min(0)
        @NotBlank(message = "가격을 입력해주세요.")
        private Integer price;
        @NotBlank(message = "상품이미지를 입력해주세요.")
        private String image;
        @NotBlank(message = "상품의 카테고리를 입력해주세요.")
        private Long categoryId;

        public Product toProduct(Category category) {
            Product product = new Product();

            product.setName(name);
            product.setPrice(price);
            product.setImage(image);
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

    @Data
    public static class UpdateProductScore {
        @NotBlank
        private String name;
        @NotBlank
        private Integer price;

    }

}
