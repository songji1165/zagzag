package com.jtrio.zagzag.model;

import com.jtrio.zagzag.enums.Evaluation;
import com.jtrio.zagzag.review.ReviewDto;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Byte productScore;
    private Byte deliveryScore;
    @ManyToMany
    private List<User> likers;
    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    public ReviewDto toReviewDto(String userId){
        ReviewDto reviewDto = new ReviewDto();

        reviewDto.setId(id);
        reviewDto.setContent(content);
        reviewDto.setProductScore(productScore);
        reviewDto.setDeliveryScore(deliveryScore);
        reviewDto.setLikers(likers.size());
        reviewDto.setCreated(created);
        reviewDto.setUpdated(updated);
        reviewDto.setUserId(userId);

        return reviewDto;
    }
}
