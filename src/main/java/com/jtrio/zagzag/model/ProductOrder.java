package com.jtrio.zagzag.model;

import com.jtrio.zagzag.enums.OrderStatus;
import com.jtrio.zagzag.order.OrderDto;
import com.jtrio.zagzag.product.ProductDto;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@EntityListeners(value = {AuditingEntityListener.class})
public class ProductOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer price; //총 금액
    private OrderStatus status;

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

    public OrderDto toOrderDto(){
        OrderDto orderDto = new OrderDto();
        ProductDto productDto = product.toProductDto();

        orderDto.setCategoryName(productDto.getCategoryName());
        orderDto.setProductName(product.getName());
        orderDto.setPrice(product.getPrice());
        orderDto.setImg(product.getImage());
        orderDto.setTotalPrice(price);
        orderDto.setStatus(status);
        orderDto.setCreated(created);
        orderDto.setUpdated(updated);

        return orderDto;
    }

}
