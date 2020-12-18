package com.jtrio.zagzag.order;

import com.jtrio.zagzag.exception.ParameterMissedException;
import com.jtrio.zagzag.exception.NotFoundException;
import com.jtrio.zagzag.model.Product;
import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.model.User;
import com.jtrio.zagzag.product.ProductRepository;
import com.jtrio.zagzag.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
    public OrderDto createOrder(OrderCommand.OrderProduct params){

        User user = userRepository.findByEmail(params.getUserEmail()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        Product product = productRepository.findById(params.getProductId()).orElseThrow(() -> new NotFoundException("해당 상품을 찾을 수 없습니다."));

        ProductOrder productOrder = params.toProductOrder(user, product);

        orderRepository.save(productOrder);

        return productOrder.toOrderDto();
    }

    public List<OrderDto> findOrder(String userId, LocalDate startDt, LocalDate endDt){
        User user = userRepository.findByEmail(userId).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));

        List<ProductOrder> allProduts;
        List<OrderDto> productsDto = new ArrayList<>();

        if(startDt == null){ //전체조회 or 시작기간에러
            if(endDt != null){ throw new ParameterMissedException("시작기간을 선택해주세요."); }

            allProduts = orderRepository.findByUser(user);

        }else{//기간조회
            /**
             * 기간 조회 : user에 해당기간 내 order 조회 해야함 ! 완성 못함
             *                  -> JPA 모르겟음 ..
             * **/
            endDt = Optional.ofNullable(endDt).orElse(LocalDate.now());

            LocalDateTime start = startDt.atTime(20,16, 40, 1600);
            LocalDateTime end = endDt.atTime(20,16, 40, 1600);

            allProduts = orderRepository.findByCreatedBetweenAndUser(start, end, user);
        }

        for(ProductOrder product : allProduts) {
            OrderDto order = product.toOrderDto();
            productsDto.add(order);
        }

        return productsDto;
    }

    public OrderDto updateOrder(Long id, OrderCommand.UpdateOrder updateCommand){
        User user = userRepository.findByEmail(updateCommand.getUserId()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));

        ProductOrder order = orderRepository.findById(id).orElseThrow(()->new NotFoundException("해당 주문을 찾을 수 없습니다."));

        if(order.getUser() == user){
            orderRepository.save(updateCommand.toProductOrder(order, updateCommand.getStatus()));

            return order.toOrderDto();

        }else{
            throw new ParameterMissedException("해당 주문의 사용자가 맞는지 확인해주세요.");
        }


    }
}
