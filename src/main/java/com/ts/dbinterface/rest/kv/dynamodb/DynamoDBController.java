package com.ts.dbinterface.rest.kv.dynamodb;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.ts.dbinterface.service.kv.dynamodb.DynamoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RestController
@RequestMapping("/kv/dynamodb")
public class DynamoDBController {

    private DynamoDB dynamoDB;

    @Autowired
    public DynamoDBController(DynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

//    table operations
    @PostMapping("/tables")
    public boolean createTable(@RequestBody Map<String, Object> dynamoDBTableInput) {
        String tableName = (String)dynamoDBTableInput.get("tableName");
        String primaryKey = (String)dynamoDBTableInput.get("primaryKey");
        long readCapacity = ((Number)dynamoDBTableInput.get("readCapacity")).longValue();
        long writeCapacity = ((Number)dynamoDBTableInput.get("writeCapacity")).longValue();

        return dynamoDB.createTable(tableName, primaryKey, readCapacity, writeCapacity);
    }

    @PostMapping("/table-with-composite-key")
    public boolean createTableWithCompositeKey(@RequestBody Map<String, Object> dynamoDBTableInput) {
        String tableName = (String)dynamoDBTableInput.get("tableName");
        String firstKey = (String)dynamoDBTableInput.get("firstKey");
        String secondKey = (String)dynamoDBTableInput.get("secondKey");
        long readCapacity = ((Number)dynamoDBTableInput.get("readCapacity")).longValue();
        long writeCapacity = ((Number)dynamoDBTableInput.get("writeCapacity")).longValue();

        return dynamoDB.createTable(tableName, firstKey, secondKey, readCapacity, writeCapacity);
    }

    @GetMapping("/tables")
    public List<String> listTables() {
        return dynamoDB.listTables();
    }

    @GetMapping("/tables/{tableName}")
    public Map<String, String> describeTable(@PathVariable String tableName) {
        Map<String, String> tableDescription = dynamoDB.describeTable(tableName);
        return tableDescription;
    }

    @PutMapping("/tables")
    public boolean updateTable(@RequestBody Map<String, Object> dynamoDBTableInput) {
        String tableName = (String)dynamoDBTableInput.get("tableName");
        long readCapacity = ((Number)dynamoDBTableInput.get("readCapacity")).longValue();
        long writeCapacity = ((Number)dynamoDBTableInput.get("writeCapacity")).longValue();

        return dynamoDB.updateTable(tableName, readCapacity, writeCapacity);
    }

    @DeleteMapping("/tables/{tableName}")
    public boolean deleteTable(@PathVariable String tableName) {
        return dynamoDB.deleteTable(tableName);
    }

//    item operations
    @GetMapping("/tables/{tableName}/items")
    public List<Map<String, AttributeValue>> readAllItems(@PathVariable String tableName) {
        return dynamoDB.readAllItems(tableName, null);
    }

    @GetMapping("/tables/{tableName}/items/{primaryKeyName}/{primaryKeyValue}")
    public Map<String, AttributeValue> readItem(@PathVariable String tableName, @PathVariable String primaryKeyName, @PathVariable String primaryKeyValue) {
        return dynamoDB.readItem(tableName, primaryKeyName, primaryKeyValue, null);
    }

    @PostMapping("/tables/{tableName}/items")
    public boolean addItem(@PathVariable String tableName, @RequestBody Map<String, Object> dynamoDBTableItemInput) {
        String primaryKeyName = (String)dynamoDBTableItemInput.get("primaryKeyName");
        String primaryKeyValue = (String)dynamoDBTableItemInput.get("primaryKeyValue");
        Map<String, String> extraFieldsInput = (Map<String, String>)dynamoDBTableItemInput.get("extraFields");
        List<String[]> extraFields = new ArrayList<>();

        for (Map.Entry<String, String> extraField : extraFieldsInput.entrySet()) {
            extraFields.add(new String[]{extraField.getKey(), extraField.getValue()});
        }

        return dynamoDB.addItem(tableName, primaryKeyName, primaryKeyValue, extraFields);
    }

    @PutMapping("/tables/{tableName}/items")
    public boolean updateItem(@PathVariable String tableName, @RequestBody Map<String, Object> dynamoDBTableItemInput) {
        String primaryKeyName = (String)dynamoDBTableItemInput.get("primaryKeyName");
        String primaryKeyValue = (String)dynamoDBTableItemInput.get("primaryKeyValue");
        Map<String, String> extraFieldsInput = (Map<String, String>)dynamoDBTableItemInput.get("extraFields");
        List<String[]> extraFields = new ArrayList<>();

        for (Map.Entry<String, String> extraField : extraFieldsInput.entrySet()) {
            extraFields.add(new String[]{extraField.getKey(), extraField.getValue()});
        }

        return dynamoDB.updateItem(tableName, primaryKeyName, primaryKeyValue, extraFields);
    }

    @DeleteMapping("/tables/{tableName}/items/{primaryKeyName}/{primaryKeyValue}")
    public boolean deleteItem(@PathVariable String tableName, @PathVariable String primaryKeyName, @PathVariable String primaryKeyValue) {
        return dynamoDB.deleteItem(tableName, primaryKeyName, primaryKeyValue);
    }
}
