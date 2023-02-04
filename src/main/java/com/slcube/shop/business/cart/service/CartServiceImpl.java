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
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public Long saveCart(CartSaveRequestDto requestDto) {

        Long itemId = requestDto.getItemId();
        int quantity = requestDto.getQuantity();
        Item item = itemRepositoryHelper.findByNotDeleted(itemRepository, itemId);
        Cart cart = Cart.createCartItem(item, quantity);
        return cartRepository.save(cart).getId();
    }

    @Override
    public CartResponseDto findCart(Long cartId) {
        Cart cart = cartRepositoryHelper.findByDeleted(cartRepository, cartId);
        return new CartResponseDto(cart);
    }

    @Override
    public Page<CartListResponseDto> findAllCarts(Pageable pageable) {
        List<CartListResponseDto> result = cartRepository.findAllCarts(pageable).stream()
                .map(CartListResponseDto::new)
                .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, result.size());
    }

    @Override
    @Transactional
    public Long updateCart(CartUpdateRequestDto requestDto) {
        Long cartId = requestDto.getCartId();
        int quantity = requestDto.getQuantity();
        Cart cart = cartRepositoryHelper.findByDeleted(cartRepository, cartId);
        cart.updateCartItem(quantity);
        return cart.getId();
    }

    @Override
    @Transactional
    public Long deleteCart(Long cartId) {
        Cart cart = cartRepositoryHelper.findByDeleted(cartRepository, cartId);
        cart.deleteCartItem();
        return cart.getId();
    }
}
