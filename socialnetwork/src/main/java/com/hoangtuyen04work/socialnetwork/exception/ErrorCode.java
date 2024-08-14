package com.hoangtuyen04work.socialnetwork.exception;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {

    USER_EXISTED(1002, "User already existed", HttpStatus.BAD_REQUEST),
    USER_NOT_EXISTED(1001, "User not existed", HttpStatus.BAD_REQUEST),
    WRONG_PASSWORD_OR_USERNAME(1003, "Wrong user name or password", HttpStatus.BAD_REQUEST),
    ACCESSDENIED(1004, "Unauthorization", HttpStatus.FORBIDDEN),
    INVALID_INPUT(1005, "Invalid input", HttpStatus.BAD_REQUEST),
    POST_NOT_EXISTED(1006, "Post not existed", HttpStatus.BAD_REQUEST),
    NOT_AUTHENTICATED(1007, "Not authenticated", HttpStatus.UNAUTHORIZED),
    POST_NOT_FOUND(1008, "Post not found", HttpStatus.NOT_FOUND),
    COMMENT_NOT_EXISTED(1009, "Comment not existed", HttpStatus.BAD_REQUEST),
    TOKEN_INVALID(1010, "Token invalid", HttpStatus.BAD_REQUEST),
    SEND_EMAIL_ERROR_CODE(1011, "Send error", HttpStatus.BAD_REQUEST),
    SEND_MSG_ERROR_CODE(1012, "Send error", HttpStatus.BAD_REQUEST),
    ;

    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.httpStatusCode = statusCode;
    }

    int code;
    String message;
    HttpStatusCode httpStatusCode;
}

