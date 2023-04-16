package com.shaderun.exception;

public class ApiWalletException extends RuntimeException{

    public ApiWalletException(String message) {
        super(message);
    }

    public ApiWalletException(String message, Throwable cause) {
        super(message, cause);
    }
}
