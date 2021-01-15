package com.jtrio.zagzag.model;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

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

//    @OneToMany
//    @JoinColumn(name="liker_id")
//    private List<Liker> likers = new ArrayList<>(); //어떤 user 가 좋아요했는지, 테이블을 따로 만든다.

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private LocalDateTime created;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

    @OneToOne
    @JoinColumn(name="order_id")
    private ProductOrder order;
}
