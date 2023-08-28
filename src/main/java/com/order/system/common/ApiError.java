package com.order.system.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.StringJoiner;


@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class ApiError {
    private Integer errorCode;
    private String message;

    public ApiError(final Integer errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", ApiError.class.getSimpleName() + "[", "]").
                add("errorCode=" + errorCode).
                add("message='" + message + "'").
                toString();
    }
}
