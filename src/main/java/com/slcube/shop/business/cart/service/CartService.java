package com.slcube.shop.business.cart.service;

import com.slcube.shop.business.cart.dto.CartListResponseDto;
import com.slcube.shop.business.cart.dto.CartResponseDto;
import com.slcube.shop.business.cart.dto.CartSaveRequestDto;
import com.slcube.shop.business.cart.dto.CartUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    public Long saveCart(CartSaveRequestDto requestDto);
    public CartResponseDto findCart(Long cartId);
    public Page<CartListResponseDto> findAllCarts(Pageable pageable);
    public Long updateCart(CartUpdateRequestDto requestDto);
    public Long deleteCart(Long cartId);
}
