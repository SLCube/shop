package com.slcube.shop.common.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
public class CustomErrorResponse {

    private LocalDateTime timestamp = LocalDateTime.now();
    private int status;
    private String error;
    private CustomErrorCode errorCode;
    private String message;

    public static ResponseEntity<CustomErrorResponse> toResponseEntity(CustomErrorCode errorCode) {
        return ResponseEntity
                .status(errorCode.getHttpStatus().value())
                .body(CustomErrorResponse.builder()
                        .status(errorCode.getHttpStatus().value())
                        .error(errorCode.getHttpStatus().name())
                        .errorCode(errorCode)
                        .message(errorCode.getErrorMessage())
                        .build());
    }

    @Builder
    private CustomErrorResponse(int status, String error, CustomErrorCode errorCode, String message) {
        this.status = status;
        this.error = error;
        this.errorCode = errorCode;
        this.message = message;
    }
}
