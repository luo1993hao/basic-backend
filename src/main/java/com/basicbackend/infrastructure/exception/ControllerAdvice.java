package com.basicbackend.infrastructure.exception;

import com.broadcastingmanage.infrastructure.HttpResult.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {
    @ExceptionHandler(Exception.class)
    public HttpResult<Void> authExceptionHandler(Exception exception) {
        log.error("system error :{}", exception.getMessage());
        return HttpResult.fail(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage());
    }
}
