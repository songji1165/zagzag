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
    private Integer likers; //좋아요 갯수
    private Boolean liked = false; //좋아요 여부
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

    public static ReviewDto toReviewDto(Review review, User user){
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(review.getId());
        reviewDto.setContent(review.getContent());
        reviewDto.setProductScore(review.getProductScore());
        reviewDto.setDeliveryScore(review.getDeliveryScore());
        reviewDto.setCreated(review.getCreated());
        reviewDto.setUpdated(review.getUpdated());
        reviewDto.setEmail(review.getUser().getEmail());

        List<User> reviewLikers = review.getLikers();
        reviewDto.setLikers(reviewLikers.size());
        for(User liker : reviewLikers){
            if(liker.equals(user)){
                reviewDto.setLiked(true);
                break;
            }
        }

        return reviewDto;
    }
}
