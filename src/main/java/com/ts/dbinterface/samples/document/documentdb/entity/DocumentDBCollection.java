package com.ts.dbinterface.samples.document.documentdb.entity;

import com.ts.dbinterface.validation.document.documentdb.DocumentDBCollectionName;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
@Lazy
public class DocumentDBCollection {

    @DocumentDBCollectionName
    private String collectionName = "default-users-collection";

    public DocumentDBCollection() {

    }

    public DocumentDBCollection(String collectionName) {
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
        return "DocumentDBCollection{" +
                "collectionName='" + collectionName + '\'' +
                '}';
    }

    @PostConstruct
    public void startDocumentDBCollection() {
        System.out.println("New instance of documentdb collection initialized : " + getCollectionName());
    }

    @PreDestroy
    public void closeDocumentDBCollection() {
        System.out.println("Closing instance of documentdb collection : " + getCollectionName());
    }
}
