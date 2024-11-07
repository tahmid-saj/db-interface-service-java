package com.ts.dbinterface.service.kv.dynamodb;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.model.*;
import com.ts.dbinterface.utils.exceptions.kv.dynamodb.DynamoDBErrorResponse;
import com.ts.dbinterface.utils.exceptions.kv.dynamodb.DynamoDBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.Valid;
import java.util.*;

@Service
public class DynamoDB implements KeyValueStore {

    private final AmazonDynamoDB ddb;

    // Constructor injection for AmazonDynamoDB
    @Autowired
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

    // Table operations

    // Creates a table with a simple primary key
    public boolean createTable(@Valid String tableName, @Valid String primaryKey, long readCapacity, long writeCapacity) {
        CreateTableRequest request = new CreateTableRequest()
                .withAttributeDefinitions(new AttributeDefinition(primaryKey, ScalarAttributeType.S))
                .withKeySchema(new KeySchemaElement(primaryKey, KeyType.HASH))
                .withProvisionedThroughput(new ProvisionedThroughput(readCapacity, writeCapacity))
                .withTableName(tableName);

        try {
            CreateTableResult result = ddb.createTable(request);
            System.out.println("Created table: " + result.getTableDescription().getTableName());
            return true;
        } catch (AmazonServiceException e) {
            System.err.println("Failed to create table: " + e.getErrorMessage());
            throw new DynamoDBException("Failed to create table: " + e.getErrorMessage());
        }
    }

    // Creates a table with a composite primary key
    public boolean createTable(@Valid String tableName, @Valid String firstKey, @Valid String secondKey, long readCapacity, long writeCapacity) {
        CreateTableRequest request = new CreateTableRequest()
                .withAttributeDefinitions(
                        new AttributeDefinition(firstKey, ScalarAttributeType.S),
                        new AttributeDefinition(secondKey, ScalarAttributeType.S)
                )
                .withKeySchema(
                        new KeySchemaElement(firstKey, KeyType.HASH),
                        new KeySchemaElement(secondKey, KeyType.RANGE)
                )
                .withProvisionedThroughput(new ProvisionedThroughput(readCapacity, writeCapacity))
                .withTableName(tableName);

        try {
            CreateTableResult result = ddb.createTable(request);
            System.out.println("Created table: " + result.getTableDescription().getTableName());
            return true;
        } catch (AmazonServiceException e) {
            System.err.println("Failed to create table: " + e.getErrorMessage());
            throw new DynamoDBException("Failed to create table: " + e.getErrorMessage());
        }
    }

    // List tables
    public List<String> listTables() {
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
                throw new DynamoDBException("Failed to list tables: " + e.getErrorMessage());
            }
        }

        return tables;
    }

    // Describe a table
    public Map<String, String> describeTable(@Valid String tableName) {
        Map<String, String> tableDescription = new HashMap<>();

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

                tableDescription.put("Table name", tableInfo.getTableName());
                tableDescription.put("Table ARN", tableInfo.getTableArn());
                tableDescription.put("Table status", tableInfo.getTableStatus());
                tableDescription.put("Item count", tableInfo.getItemCount().toString());
                tableDescription.put("Size (bytes)", tableInfo.getTableSizeBytes().toString());
                tableDescription.put("Read capacity", throughputInfo.getReadCapacityUnits().toString());
                tableDescription.put("Write capacity", throughputInfo.getWriteCapacityUnits().toString());

                List<AttributeDefinition> attributes = tableInfo.getAttributeDefinitions();
                System.out.println("Attributes");
                for (AttributeDefinition a : attributes) {
                    System.out.format(" %s (%s)\n", a.getAttributeName(), a.getAttributeType());

                    tableDescription.put(a.getAttributeName(), a.getAttributeType());
                }
            }
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            throw new DynamoDBException(e.getErrorMessage());
        }

        return tableDescription;
    }

    // Update a table
    public boolean updateTable(@Valid String tableName, long newReadCapacity, long newWriteCapacity) {
        ProvisionedThroughput tableThroughput = new ProvisionedThroughput(newReadCapacity, newWriteCapacity);

        try {
            ddb.updateTable(tableName, tableThroughput);
            System.out.println("Table was updated : " + tableName);
            return true;
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            throw new DynamoDBException(e.getErrorMessage());
        }
    }

    // Delete a table
    public boolean deleteTable(@Valid String tableName) {
        try {
            ddb.deleteTable(tableName);
            System.out.println("Table was deleted : " + tableName);
            return true;
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            throw new DynamoDBException(e.getErrorMessage());
        }
    }

    // Item operations

    // Read all items
    public List<Map<String, AttributeValue>> readAllItems(@Valid String tableName, String projectionExpression) {
        List<Map<String, AttributeValue>> items = new ArrayList<>();

        // Create the scan request
        ScanRequest scanRequest = new ScanRequest().withTableName(tableName);

        if (projectionExpression != null && !projectionExpression.isEmpty()) {
            scanRequest.withProjectionExpression(projectionExpression);
        }

        try {
            // Execute the scan and get all items
            ScanResult result;
            do {
                result = ddb.scan(scanRequest);
                items.addAll(result.getItems());

                // Print each item
                for (Map<String, AttributeValue> item : result.getItems()) {
                    System.out.println("Item: " + item);
                }

                // Set the start key for the next scan if there are more items
                scanRequest.setExclusiveStartKey(result.getLastEvaluatedKey());

            } while (result.getLastEvaluatedKey() != null); // Loop if there are more items

        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            throw new DynamoDBException(e.getErrorMessage());
        }

        return items;
    }

    // Read item
    public Map<String, AttributeValue> readItem(@Valid String tableName, @Valid String primaryKeyName, String primaryKeyValue, String projectionExpression) {
        HashMap<String, AttributeValue> keyToGet = new HashMap<String, AttributeValue>();
        keyToGet.put(primaryKeyName, new AttributeValue(primaryKeyValue));
        Map<String, AttributeValue> returnedKeyValuePairs = new HashMap<>();

        GetItemRequest request = null;
        if (projectionExpression != null) {
            request = new GetItemRequest()
                    .withKey(keyToGet)
                    .withTableName(tableName)
                    .withProjectionExpression(projectionExpression);
        } else {
            request = new GetItemRequest()
                    .withKey(keyToGet)
                    .withTableName(tableName);
        }

        try {
            Map<String, AttributeValue> returnedItem = ddb.getItem(request).getItem();
            if (returnedItem != null) {
                Set<String> keys = returnedItem.keySet();
                for (String key : keys) {
                    returnedKeyValuePairs.put(key, returnedItem.get(key));
                    System.out.format("%s: %s\n", key, returnedItem.get(key).toString());
                }
            } else {
                System.out.format("No item found with the key %s!\n", primaryKeyValue);
            }
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            throw new DynamoDBException(e.getErrorMessage());
        }

        return returnedKeyValuePairs;
    }

    // Add item
    public boolean addItem(@Valid String tableName, @Valid String key, String value, List<String[]> extraFields) {
        HashMap<String,AttributeValue> itemValues = new HashMap<String, AttributeValue>();

        itemValues.put(key, new AttributeValue(value));

        for (String[] field : extraFields) {
            itemValues.put(field[0], new AttributeValue(field[1]));
        }

        try {
            ddb.putItem(tableName, itemValues);
        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The table \"%s\" can't be found.\n", tableName);
            System.err.println("Be sure that it exists and that you've typed its name correctly!");
            throw new DynamoDBException(e.getErrorMessage());
        } catch (AmazonServiceException e) {
            System.err.println(e.getMessage());
            throw new DynamoDBException(e.getErrorMessage());
        }

        return true;
    }

    // Update item
    public boolean updateItem(@Valid String tableName, @Valid String key, String value, List<String[]> extraFields) {
        HashMap<String,AttributeValue> itemKey = new HashMap<String, AttributeValue>();
        itemKey.put(key, new AttributeValue(value));

        HashMap<String,AttributeValueUpdate> updatedValues = new HashMap<String,AttributeValueUpdate>();

        for (String[] field : extraFields) {
            updatedValues.put(field[0], new AttributeValueUpdate(new AttributeValue(field[1]), AttributeAction.PUT));
        }

        try {
            ddb.updateItem(tableName, itemKey, updatedValues);
        } catch (ResourceNotFoundException e) {
            System.err.println(e.getMessage());
            throw new DynamoDBException(e.getErrorMessage());
        } catch (AmazonServiceException e) {
            System.err.println(e.getMessage());
            throw new DynamoDBException(e.getErrorMessage());
        }

        return true;
    }

    // Delete item
    public boolean deleteItem(@Valid String tableName, @Valid String key, String value) {
        HashMap<String, AttributeValue> itemKey = new HashMap<String, AttributeValue>();
        itemKey.put(key, new AttributeValue(value));

        DeleteItemRequest deleteItemRequest = new DeleteItemRequest()
            .withTableName(tableName)
            .withKey(itemKey);

        try {
            ddb.deleteItem(deleteItemRequest);
        } catch (ResourceNotFoundException e) {
            System.err.format("Error: The table \"%s\" can't be found.\n", tableName);
            System.err.println("Be sure that it exists and that you've typed its name correctly!");
            throw new DynamoDBException(e.getErrorMessage());
        } catch (AmazonServiceException e) {
            System.err.println(e.getMessage());
            throw new DynamoDBException(e.getErrorMessage());
        }

        return true;
    }

    @ExceptionHandler
    public ResponseEntity<DynamoDBErrorResponse> handleException(DynamoDBException exception) {
        DynamoDBErrorResponse errorResponse = new DynamoDBErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<DynamoDBErrorResponse> handleException(Exception exception) {
        DynamoDBErrorResponse errorResponse = new DynamoDBErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
