package com.slcube.shop.business.cart.controller;

import com.slcube.shop.business.cart.dto.CartListResponseDto;
import com.slcube.shop.business.cart.dto.CartSaveRequestDto;
import com.slcube.shop.business.cart.dto.CartUpdateRequestDto;
import com.slcube.shop.business.cart.service.CartService;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/carts")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @PostMapping
    public Long saveCart(@RequestBody @Valid CartSaveRequestDto requestDto, @AuthenticationPrincipal MemberSessionDto sessionDto) {
        return cartService.saveCart(requestDto, sessionDto);
    }

    @GetMapping
    public Page<CartListResponseDto> findAllCarts(@AuthenticationPrincipal MemberSessionDto sessionDto, Pageable pageable) {
        return cartService.findAllCarts(sessionDto, pageable);
    }

    @PatchMapping("/{cartId}")
    public Long updateCart(@PathVariable Long cartId, @RequestBody @Valid CartUpdateRequestDto requestDto) {
        return cartService.updateCart(cartId, requestDto);
    }

    @DeleteMapping("/{cartId}")
    public Long deleteCart(@PathVariable Long cartId) {
        return cartService.deleteCart(cartId);
    }
}