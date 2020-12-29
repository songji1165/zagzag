package com.jtrio.zagzag.order;

import com.jtrio.zagzag.exception.ParameterMissedException;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
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
    @Transactional
    public OrderDto createOrder(OrderCommand.OrderProduct params){

        User user = userRepository.findByEmail(params.getUserEmail()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Product product = productRepository.findById(params.getProductId()).orElseThrow(() -> new NotFoundException("해당 상품을 찾을 수 없습니다."));

        ProductOrder productOrder = params.toProductOrder(user, product);

        orderRepository.save(productOrder);

        return OrderDto.toOrderDto(productOrder);
    }

    public Page<OrderDto> findOrder(String userId, LocalDate startDt, LocalDate endDt, Pageable pageable){
        User user = userRepository.findByEmail(userId).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));

        List<OrderDto> productsDto = new ArrayList<>();

        // 시작기간에러 // 전체조회 => 데이터가 많은 경우, 메모리 문제가 생길 수 있음!
        if(startDt == null && endDt != null) throw new ParameterMissedException("시작기간을 선택해주세요.");

        endDt = Optional.ofNullable(endDt).orElse(LocalDate.now());

        LocalDateTime start = startDt.atTime(20,16, 40, 1600);
        LocalDateTime end = endDt.atTime(20,16, 40, 1600);

        System.out.println(startDt+"======start====== : " + start);
        System.out.println(endDt+"======end====== : " + end);

        Page<ProductOrder> products = orderRepository.findByCreatedBetweenAndUser(start, end, user, pageable);

        Page<OrderDto> orderDto = products.map(product -> OrderDto.toOrderDto(product));

        return orderDto;
    }

    @Transactional
    public OrderDto updateOrder(Long id, OrderCommand.UpdateOrder updateCommand){
        User user = userRepository.findByEmail(updateCommand.getUserId()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));

        ProductOrder order = orderRepository.findById(id).orElseThrow(()->new NotFoundException("해당 주문을 찾을 수 없습니다."));

        if(order.getUser() == user){
            orderRepository.save(updateCommand.toProductOrder(order, updateCommand.getStatus()));

            return OrderDto.toOrderDto(order);

        }else{
            throw new ParameterMissedException("해당 주문의 사용자가 맞는지 확인해주세요.");
        }


    }
}
