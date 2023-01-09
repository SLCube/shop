package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.cart.CartListResponseDto;
import com.slcube.shop.business.dto.cart.CartSaveRequestDto;
import com.slcube.shop.business.dto.cart.CartUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    public Long saveCart(CartSaveRequestDto requestDto);
    public Page<CartListResponseDto> findCarts(int pageNo);
    public Long updateCart(CartUpdateRequestDto requestDto);
    public Long deleteCart(Long id);
}
