package com.slcube.shop.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<CustomErrorResponse> handleCustomException(CustomException e) {
        return CustomErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity handleException(Exception e) {
        return CustomErrorResponse.toResponseEntity(CustomErrorCode.INTERNAL_SERVER_ERROR);
    }
}
