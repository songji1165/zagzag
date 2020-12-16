package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import lombok.Data;

import javax.validation.constraints.NotBlank;

public class ReviewCommand {

    @Data
    public static class PostReview {
        @NotBlank
        private String content;
        private Byte productScore;
        private Byte deliveryScore;
        @NotBlank
        private String userId;
        @NotBlank
        private Long productId;

        public Review toReview(User user, Product product){
            Review review = new Review();

            review.setContent(content);
            review.setProductScore(productScore);
            review.setDeliveryScore(deliveryScore);
            review.setUser(user);
            review.setProduct(product);

            return review;
        }
    }

}
