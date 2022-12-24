package com.slcube.shop.business.service;

import com.slcube.shop.business.dto.cart.CartResponseDto;
import com.slcube.shop.business.dto.cart.CartSaveRequestDto;
import com.slcube.shop.business.dto.cart.CartUpdateRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    public Long registerCart(CartSaveRequestDto requestDto);
    public Page<CartResponseDto> getCarts(int pageNo);
    public Long updateCart(CartUpdateRequestDto requestDto);
    public Long deleteCart(Long id);
}
