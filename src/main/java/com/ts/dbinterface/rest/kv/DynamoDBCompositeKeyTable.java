package com.ts.dbinterface.rest.kv;

public class DynamoDBCompositeKeyTable {
    private String tableName;
    private String firstKey;
    private String secondKey;
    private long readCapacity;
    private long writeCapacity;
}
