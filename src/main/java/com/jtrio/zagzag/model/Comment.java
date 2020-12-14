package com.jtrio.zagzag.model;

import com.jtrio.zagzag.enums.CommenterType;
import com.jtrio.zagzag.enums.MessageStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(value = {AuditingEntityListener.class})
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private CommenterType commenterType;
    private MessageStatus messageStatus;
    private Boolean secret;

    @CreatedDate
    private LocalDateTime created;
    @LastModifiedDate
    private LocalDateTime updated;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name="question_id")
    private Question question;

}
