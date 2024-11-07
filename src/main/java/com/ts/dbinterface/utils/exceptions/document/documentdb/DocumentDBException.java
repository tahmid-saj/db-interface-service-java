package com.ts.dbinterface.utils.exceptions.document.documentdb;

public class DocumentDBException extends RuntimeException {

    public DocumentDBException(String message) {
        super(message);
    }

    public DocumentDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DocumentDBException(Throwable cause) {
        super(cause);
    }
}
