package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.*;
import lombok.Data;

import javax.validation.constraints.*;

public class ReviewCommand {
    @Data
    public static class CreateReview {
        @NotBlank(message = "내용을 입력해주세요.")
        private String content;
        @Min(2)
        @Max(10)
        private byte productScore;
        @Min(2)
        @Max(10)
        private byte deliveryScore;
        @NotBlank(message = "상품을 선택해주세요")
        private Long productId;
        @NotBlank(message = "주문내역을 선택해주세요.")
        private Long orderId;

        public Review toReview(User user, Product product, ProductOrder order) {
            Review review = new Review();

            review.setContent(content);
            review.setProductScore(productScore);
            review.setDeliveryScore(deliveryScore);
            review.setUser(user);
            review.setProduct(product);
            review.setOrder(order);

            return review;
        }
    }

    @Data
    public static class UpdateReview {
        private String content;
        private byte productScore;
        private byte deliveryScore;

        public Review toReview(Review review) {
            if (content != null) { review.setContent(content); }
            if (productScore >= 2) { review.setProductScore(productScore); }
            if (deliveryScore >= 2) { review.setDeliveryScore(deliveryScore); }
            return review;
        }
    }

}
