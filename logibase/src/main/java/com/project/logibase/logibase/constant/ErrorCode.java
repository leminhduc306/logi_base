package com.project.logibase.logibase.constant;

import lombok.AccessLevel;
import org.springframework.http.HttpStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    USERNAME_ALREADY_EXIST(40901, "Username đã tồn tại", HttpStatus.CONFLICT),
    USER_NOT_EXIST(40001, "Username không tồn tại", HttpStatus.BAD_REQUEST),
    GENERATE_TOKEN_FAIL(40000, "Không tạo được token", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(40100, "Tài khoản hoặc mật khẩu sai", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(40102, "Token sai hoặc quá hạn", HttpStatus.UNAUTHORIZED),
    ;
    int code;
    String message;
    HttpStatus status;
}
