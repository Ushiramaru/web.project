package com.epam.conference.dao.exception;

public class DaoTransactionException extends RuntimeException {

    public DaoTransactionException() {
    }

    public DaoTransactionException(String message) {
        super(message);
    }

    public DaoTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public DaoTransactionException(Throwable cause) {
        super(cause);
    }

    public DaoTransactionException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}