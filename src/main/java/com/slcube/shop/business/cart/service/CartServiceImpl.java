package com.slcube.shop.business.cart.service;

import com.slcube.shop.business.cart.domain.Cart;
import com.slcube.shop.business.cart.dto.CartListResponseDto;
import com.slcube.shop.business.cart.dto.CartResponseDto;
import com.slcube.shop.business.cart.dto.CartSaveRequestDto;
import com.slcube.shop.business.cart.dto.CartUpdateRequestDto;
import com.slcube.shop.business.cart.repository.CartRepository;
import com.slcube.shop.business.cart.repository.CartRepositoryHelper;
import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.item.repository.ItemRepository;
import com.slcube.shop.business.item.repository.ItemRepositoryHelper;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartRepositoryHelper cartRepositoryHelper;
    private final ItemRepository itemRepository;
    private final ItemRepositoryHelper itemRepositoryHelper;

    @Override
    @Transactional
    public Long saveCart(CartSaveRequestDto requestDto, MemberSessionDto sessionDto) {
        Long itemId = requestDto.getItemId();
        int quantity = requestDto.getQuantity();

        Item item = itemRepositoryHelper.findByNotDeleted(itemRepository, itemId);
        Cart cart = Cart.createCartItem(item, quantity, sessionDto.getMemberId());

        return cartRepository.save(cart).getId();
    }

    @Override
    public CartResponseDto findCart(Long cartId) {
        Cart cart = cartRepositoryHelper.findByNotDeleted(cartRepository, cartId);
        return new CartResponseDto(cart);
    }

    @Override
    public Page<CartListResponseDto> findAllCarts(MemberSessionDto sessionDto, Pageable pageable) {
        return cartRepository.findAllCarts(sessionDto.getMemberId(), pageable)
                .map(CartListResponseDto::new);
    }

    @Override
    @Transactional
    public Long updateCart(Long cartId, CartUpdateRequestDto requestDto) {
        int quantity = requestDto.getQuantity();

        Cart cart = cartRepositoryHelper.findByNotDeleted(cartRepository, cartId);
        cart.updateCartItem(quantity);

        return cart.getId();
    }

    @Override
    @Transactional
    public Long deleteCart(Long cartId) {
        Cart cart = cartRepositoryHelper.findByNotDeleted(cartRepository, cartId);
        cart.deleteCartItem();

        return cart.getId();
    }
}
