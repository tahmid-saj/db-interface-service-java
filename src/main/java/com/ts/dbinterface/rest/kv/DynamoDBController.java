package com.ts.dbinterface.rest.kv;

import com.ts.dbinterface.service.kv.DynamoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController()
@RequestMapping("/kv/dynamodb")
public class DynamoDBController {

    private DynamoDB dynamoDB;

    @Autowired
    public DynamoDBController(DynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
    }

    @PostMapping("/table")
    public boolean createTable(@RequestBody Map<String, Object> dynamoDBTableInput) {
        String tableName = (String)dynamoDBTableInput.get("tableName");
        String primaryKey = (String)dynamoDBTableInput.get("primaryKey");
        long readCapacity = ((Number)dynamoDBTableInput.get("readCapacity")).longValue();
        long writeCapacity = ((Number)dynamoDBTableInput.get("writeCapacity")).longValue();

        return dynamoDB.createTable(tableName, primaryKey, readCapacity, writeCapacity);
    }
}
