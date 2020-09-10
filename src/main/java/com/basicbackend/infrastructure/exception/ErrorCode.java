package com.basicbackend.infrastructure.exception;

import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

public enum ErrorCode {
    //auth
    USER_NAME_NOT_FOUND(HttpStatus.NOT_FOUND, "未找到指定用户"),
    AUTHENTICATION_FAILED(UNAUTHORIZED, "认证失败"),
    ACCESS_DENIED(FORBIDDEN, "无权访问"),

    // common,
    BAD_REQUEST_CONTENT(BAD_REQUEST, "请求内容格式错误"),
    REQUEST_VALIDATION_FAILED(BAD_REQUEST, "请求数据格式验证失败"),
    SYSTEM_ERROR(INTERNAL_SERVER_ERROR, "系统错误"),
    UPLOAD_SIZE_EXCEEDED(BAD_REQUEST, "文件过大，请重新选择"),
    USER_NAME_ALREADY_EXIST(HttpStatus.BAD_REQUEST, "用户名已经存在"),
    USER_OR_PASSWORD_ERROR(HttpStatus.BAD_REQUEST, "账号/密码错误"),
    VERIFY_JWT_FAILED(HttpStatus.UNAUTHORIZED, "token验证失败"),
    METHOD_ARGUMENT_NOT_VALID(HttpStatus.BAD_REQUEST, "方法参数验证失败"),

    // file
    FILE_UPLOAD_ERROR(INTERNAL_SERVER_ERROR, "上传文件失败"),

    FILE_COPY_ERROR(INTERNAL_SERVER_ERROR, "复制文件失败"),

    FILE_ACCESS_ERROR(INTERNAL_SERVER_ERROR, "访问文件失败"),
    ;

    private HttpStatus status;
    private String userMessage;

    ErrorCode(HttpStatus status, String userMessage) {
        this.status = status;
        this.userMessage = userMessage;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public String getUserMessage() {
        return userMessage;
    }
}
