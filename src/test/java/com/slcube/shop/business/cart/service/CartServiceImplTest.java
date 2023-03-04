package com.slcube.shop.business.cart.service;

import com.slcube.shop.business.cart.dto.CartResponseDto;
import com.slcube.shop.business.cart.dto.CartSaveRequestDto;
import com.slcube.shop.business.cart.dto.CartUpdateRequestDto;
import com.slcube.shop.business.item.domain.Item;
import com.slcube.shop.business.item.repository.ItemRepository;
import com.slcube.shop.business.member.domain.Member;
import com.slcube.shop.business.member.dto.MemberSessionDto;
import com.slcube.shop.business.member.repository.MemberRepository;
import com.slcube.shop.common.exception.CartItemNotFoundException;
import com.slcube.shop.common.security.WithMockMember;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@WithMockMember
class CartServiceImplTest {

    @Autowired
    private CartService cartService;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    private Long itemId;

    @BeforeEach
    void beforeEach() {
        Item item = Item.builder()
                .itemName("test item name")
                .price(10000)
                .stockQuantity(10)
                .build();

        itemId = itemRepository.save(item).getId();

        MemberSessionDto sessionDto = (MemberSessionDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        member = Member.builder()
                .email(sessionDto.getLoginEmail())
                .username(sessionDto.getUsername())
                .password("test password")
                .build();

        memberRepository.save(member);
    }

    @Test
    @DisplayName("장바구니에 상품 저장")
    void saveTest() {
        Long cartId = saveCart();

        CartResponseDto responseDto = cartService.findCart(cartId);

        assertAll(
                () -> assertThat(responseDto.getPrice()).isEqualTo(10000),
                () -> assertThat(responseDto.getItemName()).isEqualTo("test item name"),
                () -> assertThat(responseDto.getQuantity()).isEqualTo(5)
        );
    }

    @Test
    @DisplayName("장바구니에 저장된 상품 갯수 수정")
    void updateTest() {
        Long cartId = saveCart();

        CartUpdateRequestDto requestDto = new CartUpdateRequestDto();
        requestDto.setQuantity(15);

        Long updatedCartId = cartService.updateCart(cartId, requestDto);

        CartResponseDto cart = cartService.findCart(updatedCartId);

        assertAll(
                () -> assertThat(cart.getQuantity()).isEqualTo(15),
                () -> assertThat(cart.getPrice()).isEqualTo(10000),
                () -> assertThat(cart.getItemName()).isEqualTo("test item name")
        );
    }

    @Test
    @DisplayName("장바구니에 저장된 상품 삭제")
    void deleteTest() {
        Long cartId = saveCart();

        Long deletedId = cartService.deleteCart(cartId);

        assertThrows(CartItemNotFoundException.class,
                () -> cartService.deleteCart(deletedId));
    }

    private CartSaveRequestDto createCartItem() {
        CartSaveRequestDto requestDto = new CartSaveRequestDto();
        requestDto.setItemId(itemId);
        requestDto.setQuantity(5);
        return requestDto;
    }

    private Long saveCart() {
        MemberSessionDto sessionDto = new MemberSessionDto(member);
        CartSaveRequestDto requestDto = createCartItem();
        return cartService.saveCart(requestDto, sessionDto);
    }
}