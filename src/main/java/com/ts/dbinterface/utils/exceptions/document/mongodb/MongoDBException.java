package com.ts.dbinterface.utils.exceptions.document.mongodb;

public class MongoDBException extends RuntimeException {

    public MongoDBException(String message) {
        super(message);
    }

    public MongoDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public MongoDBException(Throwable cause) {
        super(cause);
    }
}
