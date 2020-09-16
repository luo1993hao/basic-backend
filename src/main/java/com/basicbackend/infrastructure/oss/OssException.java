package com.basicbackend.infrastructure.oss;

public class OssException  extends RuntimeException{
    public OssException(String message) {
        super(message);
    }
}
