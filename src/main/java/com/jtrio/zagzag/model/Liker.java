package com.jtrio.zagzag.model;

import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
public class Liker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId; //User를 넣어야 하나 .?
    @ManyToOne
    @JoinColumn(name="review_id")
    private Review review;

}
