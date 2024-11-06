package com.ts.dbinterface.service.kv;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.stereotype.Service;

@Service
public class DynamoDB implements KeyValueStore {

    private final AmazonDynamoDB ddb;

    // Constructor injection for AmazonDynamoDB
    public DynamoDB() {
        AmazonDynamoDB client = null;
        try {
            client = AmazonDynamoDBClientBuilder.defaultClient();
        } catch (Exception e) {
            System.err.println("Error initializing AmazonDynamoDB client: " + e.getMessage());
            throw e; // rethrow to let Spring know that bean creation failed
        }

        this.ddb = client;
    }

    // Creates a table with a simple primary key
    public void createTable(String tableName, String primaryKey) {
        CreateTableRequest request = new CreateTableRequest()
                .withAttributeDefinitions(new AttributeDefinition(primaryKey, ScalarAttributeType.S))
                .withKeySchema(new KeySchemaElement(primaryKey, KeyType.HASH))
                .withProvisionedThroughput(new ProvisionedThroughput(2L, 2L))
                .withTableName(tableName);

        try {
            CreateTableResult result = ddb.createTable(request);
            System.out.println("Created table: " + result.getTableDescription().getTableName());
        } catch (AmazonServiceException e) {
            System.err.println("Failed to create table: " + e.getErrorMessage());
        }
    }

    // Creates a table with a composite primary key
    public void createTable(String tableName, String firstKey, String secondKey) {
        CreateTableRequest request = new CreateTableRequest()
                .withAttributeDefinitions(
                        new AttributeDefinition(firstKey, ScalarAttributeType.S),
                        new AttributeDefinition(secondKey, ScalarAttributeType.S)
                )
                .withKeySchema(
                        new KeySchemaElement(firstKey, KeyType.HASH),
                        new KeySchemaElement(secondKey, KeyType.RANGE)
                )
                .withProvisionedThroughput(new ProvisionedThroughput(2L, 2L))
                .withTableName(tableName);

        try {
            CreateTableResult result = ddb.createTable(request);
            System.out.println("Created table: " + result.getTableDescription().getTableName());
        } catch (AmazonServiceException e) {
            System.err.println("Failed to create table: " + e.getErrorMessage());
        }
    }

    // List tables


    // Describe a table

    // Update a table

    // Delete a table

    // Read item

    // Add item

    // Update item
}
