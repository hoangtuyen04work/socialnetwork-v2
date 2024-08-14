package com.hoangtuyen04work.socialnetwork.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AppException extends Exception {

    private ErrorCode errorCode;
    public AppException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }


}
