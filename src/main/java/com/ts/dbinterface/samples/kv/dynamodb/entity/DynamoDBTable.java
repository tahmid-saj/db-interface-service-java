package com.ts.dbinterface.samples.kv.dynamodb.entity;

import com.ts.dbinterface.validation.kv.dynamodb.DynamoDBKey;
import com.ts.dbinterface.validation.kv.dynamodb.DynamoDBTableName;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class DynamoDBTable {

    @DynamoDBTableName
    private String tableName = "default-users-table";

    @DynamoDBKey
    private String primaryKey = "userID";
    private long readCapacity = 2L;
    private long writeCapacity = 2L;

    public DynamoDBTable() {

    }

    public DynamoDBTable(String tableName, String primaryKey) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
    }

    public DynamoDBTable(String tableName, String primaryKey, long readCapacity, long writeCapacity) {
        this.tableName = tableName;
        this.primaryKey = primaryKey;
        this.readCapacity = readCapacity;
        this.writeCapacity = writeCapacity;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(String primaryKey) {
        this.primaryKey = primaryKey;
    }

    public long getReadCapacity() {
        return readCapacity;
    }

    public void setReadCapacity(long readCapacity) {
        this.readCapacity = readCapacity;
    }

    public long getWriteCapacity() {
        return writeCapacity;
    }

    public void setWriteCapacity(long writeCapacity) {
        this.writeCapacity = writeCapacity;
    }

    @Override
    public String toString() {
        return "DynamoDBTable{" +
                "tableName='" + tableName + '\'' +
                ", primaryKey='" + primaryKey + '\'' +
                ", readCapacity=" + readCapacity +
                ", writeCapacity=" + writeCapacity +
                '}';
    }

    @PostConstruct
    public void startDynamoDBTable() {
        System.out.println("New instance of dynamodb table initialized : " + getTableName());
    }

    @PreDestroy
    public void closeDynamoDBTable() {
        System.out.println("Closing instance of dynamodb table : " + getTableName());
    }
}
