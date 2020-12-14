package com.jtrio.zagzag.order;

import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.product.ProductCommand;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.security.UserSecurity;
import com.jtrio.zagzag.user.UserCommand;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final UserSecurity userSecurity;
    private final ProductRepository productRepository;
    private final ProductOrder productOrder;
    private final OrderCommand.OrderProduct orderCommand;
    private final OrderRepository orderRepository;

    /***
     *  Spring Security
     * 1. 클라이언트 User정보 받기
     * 2. User의 정보를  인증된 User정보와 맞는지 서버에서 정보 찾기
     * 3. 인증된 정보인 경우에만, 주문 기능 사용
     *
     * ========================
     *  대체 : user정보가 등록된 user인지 확인하기
     *
     * */


    public OrderDto order(UserCommand.CheckUser checkUser, Long productId){

        User user = userSecurity.isUser(checkUser);

        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("해당 상품을 찾을 수 없습니다."));

        ProductOrder order = orderCommand.toProductOrder(user, product);

        orderRepository.save(order);
        return order.toOrderDto();
    }
}
