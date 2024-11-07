package com.ts.dbinterface.samples.document.mongodb.entity;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class UserMongoDB {

    private String userID;

    private String userFullName;

    private String userEmail;

    public UserMongoDB(String userID, String userFullName, String userEmail) {
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
        return "UserMongoDB{" +
                "userID='" + userID + '\'' +
                ", userFullName='" + userFullName + '\'' +
                ", userEmail='" + userEmail + '\'' +
                '}';
    }
}
