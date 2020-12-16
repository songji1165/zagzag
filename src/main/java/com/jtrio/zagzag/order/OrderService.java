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
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final UserRepository userRepository;
    private final UserSecurity userSecurity;
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
    public OrderDto order(OrderCommand.OrderProduct params){
//        User user = userSecurity.isUser(params.getUserId());
        User user = userRepository.findByEmail(params.getUserId()).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        List<Long> productsId = params.getProducts();
        List<Product> products = new ArrayList<>();
        Integer totalPrice = 0;

        for(Long id : productsId){
            Product product = productRepository.findById(id).orElseThrow(()->new NotFoundException("해당 상품이 존재하지 않습니다."));
            products.add(product);

            totalPrice += product.getPrice();
        }

        ProductOrder order = params.toProductOrder(user, products, totalPrice);

        orderRepository.save(order);

        return order.toOrderDto();
    }

    public List<OrderDto> findOrder(String userId, LocalDate startDt, LocalDate endDt){
        User user = userRepository.findByEmail(userId).orElseThrow(() -> new NotFoundException("해당 사용자를 찾을 수 없습니다."));
        List<ProductOrder> allProduts;
        List<OrderDto> productsDto = new ArrayList<>();

        if(startDt == null){ //전체조회 or 시작기간에러
            if(endDt != null){ throw new IllegalArgumentException("시작기간을 선택해주세요."); }

            allProduts = orderRepository.findAll();

        }else{//기간조회
            endDt = Optional.ofNullable(endDt).orElse(LocalDate.now());

            LocalDateTime start = startDt.atTime(20,16, 40, 1600);
            LocalDateTime end = endDt.atTime(20,16, 40, 1600);

            allProduts = orderRepository.findByCreatedBetween(start, end);
        }

        for(ProductOrder product : allProduts) {
            OrderDto order = product.toOrderDto();
            productsDto.add(order);
        }

        return productsDto;
    }
}
