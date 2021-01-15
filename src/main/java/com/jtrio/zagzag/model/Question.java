package com.jtrio.zagzag.model;

import com.jtrio.zagzag.enums.CommenterType;
import com.jtrio.zagzag.enums.MessageStatus;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@EntityListeners(value = {AuditingEntityListener.class})
@Data
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private CommenterType type; //구매자 여부
    private MessageStatus status; // 차단글 여부
    private Boolean secret; // 0: 일반, 1: 비밀

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @CreatedDate
    private LocalDateTime created;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @LastModifiedDate
    private LocalDateTime updated;

//    @OneToMany
//    @JoinColumn(name="comment_id")
////    @ManyToMany
//    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="user_id") //**name은 필드이름으로! 그외에 JPA는 카멜
    private User user;
    @ManyToOne
    @JoinColumn(name="product_id")
    private Product product;

}
