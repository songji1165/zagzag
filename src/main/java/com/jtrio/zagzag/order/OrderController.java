package com.jtrio.zagzag.order;

import com.jtrio.zagzag.product.ProductCommand;
import com.jtrio.zagzag.product.ProductDto;
import com.jtrio.zagzag.user.UserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/order")
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
//    public OrderDto order(@RequestBody UserCommand.CheckUser user, @RequestBody Long productId){
    public OrderDto order(@RequestBody OrderCommand.OrderProduct params){

        return orderService.order(params);
    }
}
