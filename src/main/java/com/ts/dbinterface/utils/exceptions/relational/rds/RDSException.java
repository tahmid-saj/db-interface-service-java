package com.ts.dbinterface.utils.exceptions.relational.rds;

public class RDSException extends RuntimeException {

    public RDSException(String message) {
        super(message);
    }

    public RDSException(String message, Throwable cause) {
        super(message, cause);
    }

    public RDSException(Throwable cause) {
        super(cause);
    }
}
