package com.slcube.shop.business.order.controller;

import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.order.dto.OrderCreateRequestDto;
import com.slcube.shop.business.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<Void> order(@RequestBody OrderCreateRequestDto requestDto, @AuthenticationPrincipal MemberSessionDto sessionDto) {
        orderService.order(requestDto, sessionDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
