package com.jtrio.zagzag.order;

import com.jtrio.zagzag.enums.OrderStatus;
import com.jtrio.zagzag.exception.FailedChangeException;
import com.jtrio.zagzag.exception.ParameterMissedException;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    @Transactional
    public OrderDto createOrder(SecurityUser securityUser, OrderCommand.OrderProduct params) {
        User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Product product = productRepository.findById(params.getProductId()).orElseThrow(() -> new NotFoundException("해당 상품을 찾을 수 없습니다."));

        ProductOrder productOrder = params.toProductOrder(user, product);
        orderRepository.save(productOrder);

        return OrderDto.toOrderDto(productOrder);
    }

    public Page<OrderDto> findOrder(SecurityUser securityUser, LocalDate startDt, Pageable pageable) {
        User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));

        // 시작기간에러 // 전체조회 => 데이터가 많은 경우, 메모리 문제가 생길 수 있음!
        if (startDt == null) { throw new ParameterMissedException("시작기간을 선택해주세요."); }

        LocalDateTime start = startDt.atStartOfDay();
        Page<ProductOrder> products = orderRepository.findByCreatedGreaterThanAndUser(start, user, pageable);
        Page<OrderDto> orderDto = products.map(product -> OrderDto.toOrderDto(product));

        return orderDto;
    }

    @Transactional
    public OrderDto updateOrder(Long id, SecurityUser securityUser, OrderCommand.UpdateOrder updateCommand) {
        User user = userRepository.findByEmail(securityUser.getUsername()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        ProductOrder order = orderRepository.findById(id).orElseThrow(() -> new NotFoundException("해당 주문을 찾을 수 없습니다."));

        if (!user.equals(order.getUser())) { throw new ParameterMissedException("해당 주문의 사용자가 맞는지 확인해주세요."); }

        OrderStatus orderStatus = order.getStatus();
        OrderStatus updateStatus = updateCommand.getStatus();

        if (orderStatus != OrderStatus.ORDER && updateStatus != OrderStatus.RETURN) { throw new FailedChangeException("주문 상태를 변경할 수 없습니다."); }

        orderRepository.save(updateCommand.toProductOrder(order, updateCommand.getStatus()));
        return OrderDto.toOrderDto(order);
    }

}
