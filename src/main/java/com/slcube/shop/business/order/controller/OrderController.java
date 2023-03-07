package com.slcube.shop.business.order.controller;

import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.business.order.dto.OrderResponseDto;
import com.slcube.shop.business.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> order(@RequestBody List<OrderCreateRequestDto> requestDtoList, @AuthenticationPrincipal MemberSessionDto sessionDto) {
        orderService.order(requestDtoList, sessionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{orderId}")
    public ResponseEntity<Void> cancelOrder(@PathVariable Long orderId) {
        orderService.cancelOrder(orderId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<OrderResponseDto>> getOrders(@AuthenticationPrincipal MemberSessionDto sessionDto, Pageable pageable) {
        Page<OrderResponseDto> orders = orderService.findOrders(sessionDto, pageable);
        return new ResponseEntity<>(orders, HttpStatus.OK);
    }
}
