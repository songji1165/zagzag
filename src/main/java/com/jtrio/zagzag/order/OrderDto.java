package com.jtrio.zagzag.order;

import com.jtrio.zagzag.enums.OrderStatus;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.product.ProductDto;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Long id;
    private ProductDto.OrderProductDto product;
    private Integer totalPrice;
    private OrderStatus status;
    private LocalDateTime created;
    private LocalDateTime updated;

    public static OrderDto toOrderDto(ProductOrder order) {
        ProductDto.OrderProductDto productDto = new ProductDto.OrderProductDto();

        Product product = order.getProduct();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setPrice(product.getPrice());
        productDto.setImage(product.getImage());
        productDto.setCategory(product.getCategory());

        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setProduct(productDto);
        orderDto.setTotalPrice(order.getPrice());
        orderDto.setStatus(order.getStatus());
        orderDto.setCreated(order.getCreated());
        orderDto.setUpdated(order.getUpdated());

        return orderDto;
    }

}
