package com.shaderun.exception;

public class InternalApiWalletException extends RuntimeException{

    public InternalApiWalletException(String message) {
        super(message);
    }

    public InternalApiWalletException(String message, Throwable cause) {
        super(message, cause);
    }
}
