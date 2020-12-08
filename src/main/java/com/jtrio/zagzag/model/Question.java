package com.jtrio.zagzag.model;

import com.jtrio.zagzag.enums.Status;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private Boolean purchase;
    private Boolean secret;
    private Status status;

    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;
//
//    @OneToMany
//    private List<Comment> comments;
    @ManyToOne
    @JoinColumn(name="user_id") //**name은 필드이름으로! 그외에 JPA는 카멜
    private User user;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

}
