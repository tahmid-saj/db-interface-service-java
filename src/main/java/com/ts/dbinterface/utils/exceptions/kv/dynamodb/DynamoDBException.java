package com.ts.dbinterface.utils.exceptions.kv.dynamodb;

public class DynamoDBException extends RuntimeException {

    public DynamoDBException(String message) {
        super(message);
    }

    public DynamoDBException(String message, Throwable cause) {
        super(message, cause);
    }

    public DynamoDBException(Throwable cause) {
        super(cause);
    }
}
