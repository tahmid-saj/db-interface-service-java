package com.ts.dbinterface.samples.kv.dynamodb.ops;

import com.ts.dbinterface.samples.kv.dynamodb.entity.DynamoDBTable;
import com.ts.dbinterface.service.kv.dynamodb.DynamoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DynamoDBOps {

    private DynamoDB dynamoDB;

    @Autowired
    public DynamoDBOps(DynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

    public boolean createTable(DynamoDBTable dynamoDBTable) {
        return dynamoDB.createTable(
                dynamoDBTable.getTableName(),
                dynamoDBTable.getPrimaryKey(),
                dynamoDBTable.getReadCapacity(),
                dynamoDBTable.getWriteCapacity()
        );
    }

    public List<String> listTables() {
        return dynamoDB.listTables();
    }

    public Map<String, String> describeTable(DynamoDBTable dynamoDBTable) {
        return dynamoDB.describeTable(dynamoDBTable.getTableName());
    }

    public boolean updateTable(DynamoDBTable dynamoDBTable) {
        return dynamoDB.updateTable(
                dynamoDBTable.getTableName(),
                dynamoDBTable.getReadCapacity(),
                dynamoDBTable.getWriteCapacity()
        );
    }

    public boolean deleteTable(DynamoDBTable dynamoDBTable) {
        return dynamoDB.deleteTable(dynamoDBTable.getTableName());
    }
}
