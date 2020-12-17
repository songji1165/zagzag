package com.jtrio.zagzag.order;

import com.jtrio.zagzag.enums.OrderStatus;
import com.jtrio.zagzag.product.ProductDto;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private ProductDto.OrderProduct product;
    private Integer totalPrice;
    private OrderStatus status;
    private LocalDateTime created;
    private LocalDateTime updated;

}
