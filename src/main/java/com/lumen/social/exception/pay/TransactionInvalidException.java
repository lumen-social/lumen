package com.lumen.social.exception.pay;

public class TransactionInvalidException extends RuntimeException {
    public TransactionInvalidException(String message) {
        super(message);
    }

    public TransactionInvalidException(String message, Throwable cause) {
        super(message, cause);
    }
}
