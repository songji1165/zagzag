package com.jtrio.zagzag.order;

import com.jtrio.zagzag.model.ProductOrder;
import com.jtrio.zagzag.product.ProductCommand;
import com.jtrio.zagzag.product.ProductDto;
import com.jtrio.zagzag.user.UserCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    @GetMapping
    public List<OrderDto> findOrder(
            @RequestParam(value = "userid") String userId,
            @RequestParam(value = "startdt", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDt,
            @RequestParam(value="enddt", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDt
    ){
        return orderService.findOrder(userId, startDt, endDt);
    }

}
