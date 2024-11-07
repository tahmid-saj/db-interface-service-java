package com.ts.dbinterface.samples.kv.dynamodb.dao;

import com.ts.dbinterface.samples.kv.dynamodb.entity.DynamoDBTable;
import com.ts.dbinterface.samples.kv.dynamodb.entity.User;
import com.ts.dbinterface.service.kv.dynamodb.DynamoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class UserDAOImpl implements UserDAO {

    private DynamoDB dynamoDB;
    private DynamoDBTable dynamoDBTable;

    @Autowired
    public UserDAOImpl(DynamoDB dynamoDB) {
        this.dynamoDB = dynamoDB;
        this.dynamoDBTable = new DynamoDBTable();
    }

    @Override
    public List<User> findAll() {
        return Collections.singletonList((User) dynamoDB.readAllItems(dynamoDBTable.getTableName(), null));
    }

    @Override
    public User findById(String userID) {
        return (User) dynamoDB.readItem(dynamoDBTable.getTableName(), dynamoDBTable.getPrimaryKey(), userID, null);
    }

    @Override
    public boolean add(User user) {
        List<String[]> userInfo = new ArrayList<>();
        userInfo.add(new String[]{"userFullName", user.getUserFullName()});
        userInfo.add(new String[]{"userEmail", user.getUserEmail()});

        return dynamoDB.addItem(
                dynamoDBTable.getTableName(),
                dynamoDBTable.getPrimaryKey(),
                user.getUserID(),
                userInfo
        );
    }

    @Override
    public boolean update(User user) {
        List<String[]> userInfo = new ArrayList<>();
        userInfo.add(new String[]{"userFullName", user.getUserFullName()});
        userInfo.add(new String[]{"userEmail", user.getUserEmail()});

        return dynamoDB.updateItem(
                dynamoDBTable.getTableName(),
                dynamoDBTable.getPrimaryKey(),
                user.getUserID(),
                userInfo
        );
    }

    @Override
    public boolean delete(String userID) {
        return dynamoDB.deleteItem(
                dynamoDBTable.getTableName(),
                dynamoDBTable.getPrimaryKey(),
                userID
        );
    }
}
