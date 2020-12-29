package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.*;
import lombok.Data;

import javax.validation.constraints.*;

public class ReviewCommand {

    @Data
    public static class createReview {
        @NotBlank(message = "내용을 입력해주세요.")
        private String content;
        @Size(min=0, max=10)
        private byte productScore;
        @Size(min=0, max=10)
        private byte deliveryScore;
        @Email
        private String userEmail;
        @NotBlank(message = "상품을 선택해주세요")
        private Long productId;
        @NotBlank(message = "주문내역을 선택해주세요.")
        private Long orderId;

        public Review toReview(User user, Product product, ProductOrder order){
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

}
