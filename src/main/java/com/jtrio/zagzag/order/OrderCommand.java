package com.jtrio.zagzag.order;

import com.jtrio.zagzag.enums.OrderStatus;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.user.UserCommand;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.NotBlank;

public class OrderCommand {

    @Data
    public static class OrderProduct{
        @NotBlank
        private String name;
        @NotBlank
        private Integer price;
        private OrderStatus status = OrderStatus.ORDER;

        public ProductOrder toProductOrder(User user, Product product){
            ProductOrder order = new ProductOrder();

            order.setPrice(price);
            order.setStatus(status);
            order.setUser(user);
            order.setProduct(product);

            return order;
        }
    }
}
