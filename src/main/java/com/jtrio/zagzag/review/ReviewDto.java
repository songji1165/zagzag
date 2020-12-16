package com.jtrio.zagzag.review;

import com.jtrio.zagzag.model.Category;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.user.UserDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ReviewDto {
    private Long id;
    private String content;
    private Byte productScore;
    private Byte deliveryScore;
    private Integer likers; //리스트 갯수
    private LocalDateTime created;
    private LocalDateTime updated;
    private String userId;
}
