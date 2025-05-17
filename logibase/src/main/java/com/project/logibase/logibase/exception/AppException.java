package com.project.logibase.logibase.exception;

import com.project.logibase.logibase.constant.ErrorCode;
import lombok.Getter;

@Getter
public class AppException extends RuntimeException {

    ErrorCode errorCode;

    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
