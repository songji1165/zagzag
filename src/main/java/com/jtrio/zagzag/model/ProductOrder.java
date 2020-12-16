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
    @OneToMany
    @JoinColumn(name="product_id")
    private List<Product> products;

    public OrderDto toOrderDto(){
        OrderDto orderDto = new OrderDto();

        List<ProductDto.OrderProduct> productsList = new ArrayList<>();
        for(Product product : products){
            ProductDto.OrderProduct orderProduct = new ProductDto.OrderProduct();

            orderProduct.setId(product.getId());
            orderProduct.setName(product.getName());
            orderProduct.setPrice(product.getPrice());
            orderProduct.setImage(product.getImage());
            orderProduct.setCategory(product.getCategory());

            productsList.add(orderProduct);
        }

        orderDto.setId(id);
        orderDto.setProducts(productsList);
        orderDto.setTotalPrice(price);
        orderDto.setStatus(status);
        orderDto.setCreated(created);
        orderDto.setUpdated(updated);

        return orderDto;
    }

}
