package com.jtrio.zagzag.order;

import com.jtrio.zagzag.enums.OrderStatus;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private String categoryName;
    private String productName;
    private Integer price;
    private String img;
    private Integer totalPrice;
    private OrderStatus status;

    private LocalDateTime created;
    private LocalDateTime updated;

}
