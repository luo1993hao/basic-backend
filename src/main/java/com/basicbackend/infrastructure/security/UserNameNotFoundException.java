package com.basicbackend.infrastructure.security;

import com.broadcastingmanage.infrastructure.exception.AppException;
import com.broadcastingmanage.infrastructure.exception.ErrorCode;

import java.util.Map;

/**
 */
public class UserNameNotFoundException extends AppException {
    public UserNameNotFoundException(Map<String, Object> data) {
        super(ErrorCode.USER_NAME_NOT_FOUND, data);
    }
}
