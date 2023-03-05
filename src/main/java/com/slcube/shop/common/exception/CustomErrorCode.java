package com.slcube.shop.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@AllArgsConstructor
@Getter
public enum CustomErrorCode {

    ADDRESS_NOT_REGISTER_ANYMORE(BAD_REQUEST, "배송지를 더이상 등록할 수 없습니다."),

    INVALID_EMAIL_OR_PASSWORD(UNAUTHORIZED, "아이디 혹은 비밀번호를 확인해주세요."),
    SESSION_EXPIRED(UNAUTHORIZED, "세션이 만료되었습니다."),

    DISABLED_MEMBER(FORBIDDEN, "비활성화된 계정입니다."),

    REVIEWS_NOT_FOUND(NOT_FOUND, "리뷰 정보를 찾을 수 없습니다."),
    REPORTED_REVIEWS_NOT_FOUND(NOT_FOUND, "신고된 리뷰 정보를 찾을 수 없습니다."),
    CART_ITEMS_NOT_FOUND(NOT_FOUND, "장바구니의 상품 정보를 찾을 수 없습니다."),
    CATEGORIES_NOT_FOUND(NOT_FOUND, "카테고리 정보를 찾을 수 없습니다."),
    ADDRESS_NOT_FOUND(NOT_FOUND, "배송지 정보를 찾을 수 없습니다."),
    ITEMS_NOT_FOUND(NOT_FOUND, "상품 정보를 찾을 수 없습니다."),
    ORDER_NOT_FOUND(NOT_FOUND, "주문 정보를 찾을 수 없습니다."),
    MEMBER_NOT_FOUND(NOT_FOUND, "계정 정보를 찾을 수 없습니다."),

    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다.");

    private HttpStatus httpStatus;
    private String errorMessage;
}
