package com.ductrungsl.identity_service.exception;

public enum ErrorCode {
    UNCATEGORIZED(9999, "Uncategorized error"),
    INVALID_KEY(1001, "Invalid message key"),
    USER_EXISTED(1002, "User already exists"),
    USERNAME_INVALID(1003, "Username at least 3 characters"),
    PASSWORD_INVALID(1004, "Password at least 8 characters"),
    INFO_INVALID(1005, "Missing information"),
    ;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
