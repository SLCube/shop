package com.slcube.shop.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {CustomException.class})
    protected ResponseEntity<CustomErrorResponse> handleCustomException(CustomException e) {
        return CustomErrorResponse.toResponseEntity(e.getErrorCode());
    }

    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity handleException(Exception e) {
        log.error("internal server error", e);
        return CustomErrorResponse.toResponseEntity(CustomErrorCode.INTERNAL_SERVER_ERROR);
    }
}
