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
import java.util.ArrayList;
import java.util.List;

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
//    @ManyToOne
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    public OrderDto toOrderDto(){
        ProductDto.OrderProductDto productDto = new ProductDto.OrderProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        productDto.setCategory(product.getCategory());

        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        orderDto.setProduct(productDto);
        orderDto.setTotalPrice(price);
        orderDto.setStatus(status);
        orderDto.setCreated(created);
        orderDto.setUpdated(updated);

        return orderDto;
    }

}
