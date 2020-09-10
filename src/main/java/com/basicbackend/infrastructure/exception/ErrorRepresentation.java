package com.basicbackend.infrastructure.exception;

import java.time.Instant;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;
import static java.time.Instant.now;
import static java.util.Collections.unmodifiableMap;
import static org.springframework.util.CollectionUtils.isEmpty;

public final class ErrorRepresentation {
    private final ErrorCode code;
    private final int status;
    private final String message;
    private final String path;
    private final Instant timestamp;
    private final Map<String, Object> data = newHashMap();

    private ErrorRepresentation(AppException ex, String path) {
        this.code = ex.getCode();
        this.status = ex.httpStatus().value();
        this.message = ex.userMessage();
        this.path = path;
        this.timestamp = now();
        if (!isEmpty(ex.getData())) {
            this.data.putAll(ex.getData());
        }
    }

    public ErrorRepresentation(ErrorCode code, int status, String message, String path, Map<String, Object> data) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.path = path;
        this.timestamp = now();
        if (!isEmpty(data)) {
            this.data.putAll(data);
        }
    }

    public static ErrorRepresentation from(AppException ex, String path) {
        return new ErrorRepresentation(ex, path);
    }

    public ErrorCode getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public Map<String, Object> getData() {
        return unmodifiableMap(data);
    }

}
