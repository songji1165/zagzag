package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDto {
    private Long id;
    private String content;
    private byte productScore;
    private byte deliveryScore;
    private Long likers; //좋아요 갯수
    private Boolean liked = false; //좋아요 여부
    private LocalDateTime created;
    private LocalDateTime updated;
    private String email;
    private Boolean myReveiw = false;

    public static ReviewDto toReviewDto(Review review, Long likers, String userEmail, boolean liked) {
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setProductScore(review.getProductScore());
        reviewDto.setDeliveryScore(review.getDeliveryScore());
        reviewDto.setCreated(review.getCreated());
        reviewDto.setUpdated(review.getUpdated());
        reviewDto.setEmail(review.getUser().getEmail());
        reviewDto.setLikers(likers);
        reviewDto.setLiked(liked);
        reviewDto.setMyReveiw(userEmail.equals(review.getUser().getEmail()) ? true : false);

        return reviewDto;
    }
}
