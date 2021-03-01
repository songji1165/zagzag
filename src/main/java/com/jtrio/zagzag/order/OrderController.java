package com.jtrio.zagzag.order;

import com.jtrio.zagzag.security.SecurityUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public OrderDto createOrder(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody OrderCommand.OrderProduct params) {
        return orderService.createOrder(securityUser, params);
    }

    @GetMapping
    public Page<OrderDto> findOrder(
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestParam(value = "startdt", required = false)
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDt,
            @PageableDefault() Pageable pageable) {
        return orderService.findOrder(securityUser, startDt, pageable);
    }

    @PutMapping("/{id}")
    public OrderDto updateOrder(
            @PathVariable Long id,
            @AuthenticationPrincipal SecurityUser securityUser,
            @RequestBody OrderCommand.UpdateOrder updateOrder) {
        return orderService.updateOrder(id, securityUser, updateOrder);
    }

}
