package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.*;
import com.jtrio.zagzag.user.UserDto;
import lombok.Data;

import javax.persistence.criteria.CriteriaBuilder;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewDto {
    private Long id;
    private String content;
    private byte productScore;
    private byte deliveryScore;
    private Integer likers; //좋아요 갯수
    private Boolean liked = false; //좋아요 여부
    private LocalDateTime created;
    private LocalDateTime updated;
    private String email;
    private Boolean myReveiw = false;

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

    public static ReviewDto toReviewDto(Review review, String userEmail, boolean booleanReview){
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setProductScore(review.getProductScore());
        reviewDto.setDeliveryScore(review.getDeliveryScore());
        reviewDto.setCreated(review.getCreated());
        reviewDto.setUpdated(review.getUpdated());
        reviewDto.setEmail(review.getUser().getEmail());
        reviewDto.setLikers(review.getLikers().size());
        reviewDto.setLiked(booleanReview);

        if(userEmail.equals(review.getUser().getEmail())){
            reviewDto.setMyReveiw(true);
        }

        return reviewDto;
    }
}
