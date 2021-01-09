package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.product.ProductCommand;
import com.jtrio.zagzag.product.ProductDto;
import com.jtrio.zagzag.security.SecurityUser;
import com.jtrio.zagzag.user.UserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    /**
    * 1. 주문하기
     *      - 사용자 조회
     *      - 상품 조회
     *      - 주문추가
     *      - return 주문내용
     *
     * 2. 주문조회
     *      - 사용자 조회
     *      - 전체 주문 상품 조회
     *      - 기간별 상품 조회
     *
     * 3. 주문삭제
     *      - 사용자 조회
    * */

    @PostMapping
    public OrderDto createOrder(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody OrderCommand.OrderProduct params){
        return orderService.createOrder(securityUser, params);
    }

    @GetMapping
    public Page<OrderDto> findOrder(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestParam(value = "startdt", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDt,
            @PageableDefault() Pageable pageable
    ){
        return orderService.findOrder(securityUser, startDt, pageable);
    }

    @PutMapping("/{id}")
    public OrderDto updateOrder(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody OrderCommand.UpdateOrder updateOrder){
        return orderService.updateOrder(securityUser, updateOrder);
    }

}
