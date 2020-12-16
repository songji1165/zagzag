package com.jtrio.zagzag.order;

import com.jtrio.zagzag.enums.OrderStatus;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.product.ProductDto;
import com.jtrio.zagzag.user.UserCommand;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderCommand {

    @Data
    public static class OrderProduct{

        @NotBlank
        private String userId;
        @NotBlank
        private List<Long> products;
        private OrderStatus status = OrderStatus.ORDER;

        public ProductOrder toProductOrder(User user, List<Product> products, Integer price){
            ProductOrder order = new ProductOrder();

            order.setPrice(price);
            order.setStatus(status);
            order.setUser(user);
            order.setProducts(products);

            return order;
        }

    }
}
