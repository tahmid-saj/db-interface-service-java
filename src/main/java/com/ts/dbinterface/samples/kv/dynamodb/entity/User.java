package com.ts.dbinterface.samples.kv.dynamodb.entity;

import com.ts.dbinterface.validation.kv.dynamodb.DynamoDBKey;
import com.ts.dbinterface.validation.kv.dynamodb.DynamoDBValue;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class User {
    @DynamoDBKey
    private String userID;

    @DynamoDBValue
    private String userFullName;

    @DynamoDBValue
    private String userEmail;

    public User(String userID, String userFullName, String userEmail) {
        this.userID = userID;
        this.userFullName = userFullName;
        this.userEmail = userEmail;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "User{" +
                "userID=" + userID +
                ", userFullName='" + userFullName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
