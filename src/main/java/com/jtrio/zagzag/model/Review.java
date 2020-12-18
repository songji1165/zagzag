package com.jtrio.zagzag.model;

import com.jtrio.zagzag.enums.Evaluation;
import com.jtrio.zagzag.review.ReviewDto;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private byte productScore;
    private byte deliveryScore;
    @ManyToMany
    private List<User> likers = new ArrayList<>();
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
}
