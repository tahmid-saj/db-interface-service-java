package com.ts.dbinterface.service.kv;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
    public List<String> listTables(String tableName) {
        ListTablesRequest request;
        boolean moreTables = true;
        String lastTableName = null;
        List<String> tables = new ArrayList<>();

        while (moreTables) {
            try {
                if (lastTableName == null) {
                    request = new ListTablesRequest().withLimit(10);
                } else {
                    request = new ListTablesRequest().withLimit(10).withExclusiveStartTableName(lastTableName);
                }

                ListTablesResult tableList = ddb.listTables(request);
                List<String> tableNames = tableList.getTableNames();

                if (tableNames.size() > 0) {
                    for (String currTableName : tableNames) {
                        tables.add(currTableName);
                        System.out.format("* %s\n", currTableName);
                    }
                } else {
                    System.out.println("No tables found");
                    System.exit(0);
                }

                lastTableName = tableList.getLastEvaluatedTableName();
                if (lastTableName == null) {
                    moreTables = false;
                }
            } catch (AmazonServiceException e) {
                System.err.println("Failed to list tables: " + e.getErrorMessage());
            }
        }

        return tables;
    }

    // Describe a table
    public void describeTable(String tableName) {
        try {
            TableDescription tableInfo = ddb.describeTable(tableName).getTable();

            if (tableInfo != null) {
                System.out.format("Table name : %s\n", tableInfo.getTableName());
                System.out.format("Table ARN : %s\n", tableInfo.getTableArn());
                System.out.format("Status : %s\n", tableInfo.getTableStatus());
                System.out.format("Item count : %d\n", tableInfo.getItemCount().longValue());
                System.out.format("Size (bytes) : %d\n", tableInfo.getTableSizeBytes().longValue());

                ProvisionedThroughputDescription throughputInfo = tableInfo.getProvisionedThroughput();
                System.out.println("Throughput");
                System.out.format(" Read Capacity : %d\n", throughputInfo.getReadCapacityUnits().longValue());
                System.out.format(" Write Capacity : %d\n", throughputInfo.getWriteCapacityUnits().longValue());

                List<AttributeDefinition> attributes = tableInfo.getAttributeDefinitions();
                System.out.println("Attributes");
                for (AttributeDefinition a : attributes) {
                    System.out.format(" %s (%s)\n", a.getAttributeName(), a.getAttributeType());
                }
            }
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }

    // Update a table
    public void updateTable(String tableName, long newReadCapacity, long newWriteCapacity) {
        ProvisionedThroughput tableThroughput = new ProvisionedThroughput(newReadCapacity, newWriteCapacity);

        try {
            ddb.updateTable(tableName, tableThroughput);
            System.out.println("Table was updated");
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            System.exit(1);
        }
    }

    // Delete a table

    // Read item

    // Add item

    // Update item
}
