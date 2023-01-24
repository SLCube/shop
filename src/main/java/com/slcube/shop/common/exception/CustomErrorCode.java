package com.slcube.shop.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;


@AllArgsConstructor
@Getter
public enum CustomErrorCode {
    REVIEWS_NOT_FOUND(NOT_FOUND, "리뷰 정보를 찾을 수 없습니다."),

    ITEMS_NOT_FOUND(NOT_FOUND, "상품 정보를 찾을 수 없습니다."),
    MEMBER_NOT_FOUND(NOT_FOUND, "계정 정보를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 에러입니다.");

    private HttpStatus httpStatus;
    private String errorMessage;
}
