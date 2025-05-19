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
    TITLE_ALREADY_EXIST(40901, "Tiêu đề đã tồn tại", HttpStatus.CONFLICT),
    USERNAME_ALREADY_EXIST(40900, "Username đã tồn tại", HttpStatus.CONFLICT),
    GENERATE_TOKEN_FAIL(40000, "Không tạo được token", HttpStatus.BAD_REQUEST),
    USER_NOT_EXIST(40001, "Username không tồn tại", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL(40002, "Email không đúng định dạng", HttpStatus.BAD_REQUEST),
    INVALID_PHONE_NUMBER(40003, "Số điện thoại bao gồm 10 chữ số", HttpStatus.BAD_REQUEST),
    INVALID_PASSWORD(40004, "Password phải có ít nhất 8 kí tự, 1 kí tự hoa, 1 kí tự số", HttpStatus.BAD_REQUEST),
    INVALID_NAME(40005, "Tên chỉ chứa kí tự thường", HttpStatus.BAD_REQUEST),
    TITLE_NOT_NULL(40006, "Tiêu đề không được để trống", HttpStatus.BAD_REQUEST),
    PRICE_LOWER_ZERO(40007,"Giá khóa học phải lớn hơn 0", HttpStatus.BAD_REQUEST),
    COURSE_NOT_EXIST(40008, "Khóa học không tồn tại", HttpStatus.BAD_REQUEST),
    UNAUTHORIZED(40100, "Tài khoản hoặc mật khẩu sai", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(40101, "Token sai hoặc quá hạn", HttpStatus.UNAUTHORIZED),
    ;
    int code;
    String message;
    HttpStatus status;
}
