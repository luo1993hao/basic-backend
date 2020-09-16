package com.basicbackend.infrastructure.sms;

public class SmsException extends RuntimeException{
    public SmsException(String message) {
        super(message);
    }
}
