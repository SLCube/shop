package com.slcube.shop.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@AllArgsConstructor
@Getter
public enum CustomErrorCode {

    ADDRESS_NOT_REGISTER_ANYMORE(BAD_REQUEST, "배송지를 더이상 등록할 수 없습니다."),
    ORDER_ALREADY_CANCEL(BAD_REQUEST, "이미 취소된 주문입니다."),
    NOT_ENOUGH_STOCK_QUANTITY(BAD_REQUEST, "상품 재고가 충분하지 않습니다."),
    REVIEWS_NOT_FOUND(BAD_REQUEST, "리뷰 정보를 찾을 수 없습니다."),
    REPORTED_REVIEWS_NOT_FOUND(BAD_REQUEST, "신고된 리뷰 정보를 찾을 수 없습니다."),
    CART_ITEMS_NOT_FOUND(BAD_REQUEST, "장바구니의 상품 정보를 찾을 수 없습니다."),
    CATEGORIES_NOT_FOUND(BAD_REQUEST, "카테고리 정보를 찾을 수 없습니다."),
    ADDRESS_NOT_FOUND(BAD_REQUEST, "배송지 정보를 찾을 수 없습니다."),
    ITEMS_NOT_FOUND(BAD_REQUEST, "상품 정보를 찾을 수 없습니다."),
    ORDER_NOT_FOUND(BAD_REQUEST, "주문 정보를 찾을 수 없습니다."),
    MEMBER_NOT_FOUND(BAD_REQUEST, "계정 정보를 찾을 수 없습니다."),
    ITEM_VALIDATION_NOT_CORRECT(BAD_REQUEST, "상품 정보가 유효하지 않습니다."),
    DUPLICATED_EMAIL(BAD_REQUEST, "이미 등록된 이메일입니다."),

    INVALID_EMAIL_OR_PASSWORD(UNAUTHORIZED, "아이디 혹은 비밀번호를 확인해주세요."),
    SESSION_EXPIRED(UNAUTHORIZED, "세션이 만료되었습니다."),

    DISABLED_MEMBER(FORBIDDEN, "비활성화된 계정입니다."),


    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다.");

    private HttpStatus httpStatus;
    private String errorMessage;
}
