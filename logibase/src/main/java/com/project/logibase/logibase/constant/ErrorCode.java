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

    USERNAME_ALREADY_EXIST(40901, "Username is already exist", HttpStatus.CONFLICT),
    USER_NOT_EXIST(40001, "User is not exist", HttpStatus.BAD_REQUEST),
    ;
    int code;
    String message;
    HttpStatus status;
}
