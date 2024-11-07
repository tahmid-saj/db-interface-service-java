package com.ts.dbinterface.samples.document.mongodb.entity;

import com.ts.dbinterface.validation.document.mongodb.MongoDBCollectionName;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class MongoDBCollection {

    @MongoDBCollectionName
    private String collectionName = "default-users-collection";

    public MongoDBCollection() {

    }

    public MongoDBCollection(String collectionName) {
        this.collectionName = collectionName;
    }

    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    @Override
    public String toString() {
        return "MongoDBCollection{" +
                "collectionName='" + collectionName + '\'' +
                '}';
    }

    @PostConstruct
    public void startMongoDBCollection() {
        System.out.println("New instance of mongodb collection initialized : " + getCollectionName());
    }

    @PreDestroy
    public void closeMongoDBCollection() {
        System.out.println("Closing instance of mongodb collection : " + getCollectionName());
    }
}
