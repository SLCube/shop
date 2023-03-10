package com.slcube.shop.business.cart.service;

import com.slcube.shop.business.cart.dto.CartListResponseDto;
import com.slcube.shop.business.cart.dto.CartResponseDto;
import com.slcube.shop.business.cart.dto.CartSaveRequestDto;
import com.slcube.shop.business.cart.dto.CartUpdateRequestDto;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface CartService {

    Long saveCart(CartSaveRequestDto requestDto, MemberSessionDto sessionDto);
    CartResponseDto findCart(Long cartId);
    Page<CartListResponseDto> findAllCarts(MemberSessionDto sessionDto, Pageable pageable);
    Long updateCart(Long cartId, CartUpdateRequestDto requestDto);
    Long deleteCart(Long cartId);
}
