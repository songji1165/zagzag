package com.jtrio.zagzag.order;

import com.jtrio.zagzag.enums.OrderStatus;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.product.ProductDto;
import com.jtrio.zagzag.user.UserCommand;
import lombok.Data;
import org.springframework.boot.context.properties.bind.DefaultValue;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class OrderCommand {

    @Data
    public static class OrderProduct{

        @Email
        private String userEmail;
        @NotBlank(message = "상품을 선택해주세요.")
        private Long productId;
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

    @Data
    public static class UpdateOrder{

        @NotBlank
        private String userId;
        @NotBlank
        private OrderStatus status;

        public ProductOrder toProductOrder(ProductOrder productOrder, OrderStatus status){

            productOrder.setStatus(status);

            return productOrder;
        }

    }
}
