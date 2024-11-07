package com.ts.dbinterface.service.document.mongodb;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.ts.dbinterface.service.document.DocumentStore;
import com.ts.dbinterface.utils.exceptions.document.mongodb.MongoDBErrorResponse;
import com.ts.dbinterface.utils.exceptions.document.mongodb.MongoDBException;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@Service
public class MongoDB implements DocumentStore {

    private MongoClient mongoClient;
    private MongoDatabase database;

    @Value("${mongodb.connectionString}")
    private String mongoDBConnectionString;

    @Autowired
    public MongoDB() {
        try {
            ConnectionString connectionString = new ConnectionString(mongoDBConnectionString);
            this.mongoClient = MongoClients.create(connectionString);
            this.database = mongoClient.getDatabase(connectionString.getDatabase());
        } catch (Exception e) {
            System.err.println("Error initializing MongoDB client: " + e.getMessage());
            throw new MongoDBException("Failed to initialize MongoDB client", e);
        }
    }

    // Add getter for the database if needed
    public MongoDatabase getDatabase() {
        return this.database;
    }

    // Collection operations
    public boolean createCollection(String collectionName) {
        try {
            database.createCollection(collectionName);
            System.out.println("Created collection: " + collectionName);
            return true;
        } catch (Exception e) {
            throw new MongoDBException("Failed to create collection: " + collectionName, e);
        }
    }

    public boolean deleteCollection(String collectionName) {
        try {
            MongoCollection<Document> collection = database.getCollection(collectionName);
            collection.drop();
            System.out.println("Deleted collection: " + collectionName);
            return true;
        } catch (Exception e) {
            throw new MongoDBException("Failed to delete collection: " + collectionName, e);
        }
    }

    // Document operations
    public List<Document> findAllDocuments(String collectionName) {
        List<Document> documents = new ArrayList<>();
        try {
            MongoCollection<Document> collection = database.getCollection(collectionName);
            for (Document doc : collection.find()) {
                documents.add(doc);
                System.out.println("Document: " + doc.toJson());
            }
        } catch (Exception e) {
            throw new MongoDBException("Failed to find all documents in collection: " + collectionName, e);
        }
        return documents;
    }

    public Document findDocument(String collectionName, Document query) {
        try {
            MongoCollection<Document> collection = database.getCollection(collectionName);
            Document document = collection.find(query).first();
            if (document != null) {
                System.out.println("Found document: " + document.toJson());
            } else {
                System.out.println("No document matched the query.");
            }
            return document;
        } catch (Exception e) {
            throw new MongoDBException("Failed to find document in collection: " + collectionName, e);
        }
    }

    public boolean createDocument(String collectionName, Document document) {
        try {
            MongoCollection<Document> collection = database.getCollection(collectionName);
            collection.insertOne(document);
            System.out.println("Inserted document: " + document.toJson());
            return true;
        } catch (Exception e) {
            throw new MongoDBException("Failed to insert document into collection: " + collectionName, e);
        }
    }

    public boolean modifyDocument(String collectionName, Document query, Document update) {
        try {
            MongoCollection<Document> collection = database.getCollection(collectionName);
            Document result = collection.findOneAndUpdate(query, new Document("$set", update));
            if (result != null) {
                System.out.println("Updated document: " + result.toJson());
            } else {
                System.out.println("No document matched the query for update.");
            }
            return true;
        } catch (Exception e) {
            throw new MongoDBException("Failed to update document in collection: " + collectionName, e);
        }
    }

    public boolean deleteDocument(String collectionName, Document query) {
        try {
            MongoCollection<Document> collection = database.getCollection(collectionName);
            Document result = collection.findOneAndDelete(query);
            if (result != null) {
                System.out.println("Deleted document: " + result.toJson());
            } else {
                System.out.println("No document matched the query for deletion.");
            }
            return true;
        } catch (Exception e) {
            throw new MongoDBException("Failed to delete document from collection: " + collectionName, e);
        }
    }

    @ExceptionHandler
    public ResponseEntity<MongoDBErrorResponse> handleException(MongoDBException exception) {
        MongoDBErrorResponse errorResponse = new MongoDBErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler
    public ResponseEntity<MongoDBErrorResponse> handleException(Exception exception) {
        MongoDBErrorResponse errorResponse = new MongoDBErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                exception.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
