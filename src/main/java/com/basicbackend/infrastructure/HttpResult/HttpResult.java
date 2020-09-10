package com.basicbackend.infrastructure.HttpResult;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
public class HttpResult<T> {
    public int code;

    public String message;

    public T data;

    public static <T> HttpResult<T> success(T data, String message) {
        return new HttpResult<>(HttpStatus.OK.value(), message, data);
    }

    public static <T> HttpResult<T> fail(HttpStatus httpStatus, String message) {
        return new HttpResult<>(HttpStatus.OK.value(), message, null);
    }

    public static <T> HttpResult<T> success() {
        return new HttpResult<>(HttpStatus.OK.value(), "success", null);
    }

    public static <T> HttpResult<T> success(T data) {
        return new HttpResult<>(HttpStatus.OK.value(), "success", data);
    }
}
