package com.slcube.shop.business.cart.controller;

import com.slcube.shop.business.cart.dto.CartSaveRequestDto;
import com.slcube.shop.business.cart.service.CartService;
import com.slcube.shop.common.security.authenticationContext.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public Long saveCart(@RequestBody @Valid CartSaveRequestDto requestDto, @AuthenticationPrincipal MemberContext memberContext) {
        return cartService.saveCart(requestDto, memberContext.getMember());
    }
}