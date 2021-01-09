package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.Review;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.user.UserDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewDto {
    private Long id;
    private String content;
    private byte productScore;
    private byte deliveryScore;
    private Integer likers; //리스트 갯수
    private LocalDateTime created;
    private LocalDateTime updated;
    private String email;

    public static ReviewDto toReviewDto(Review review){
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setProductScore(review.getProductScore());
        reviewDto.setDeliveryScore(review.getDeliveryScore());
        reviewDto.setLikers(review.getLikers().size());
        reviewDto.setCreated(review.getCreated());
        reviewDto.setUpdated(review.getUpdated());
        reviewDto.setEmail(review.getUser().getEmail());

        return reviewDto;
    }
}
