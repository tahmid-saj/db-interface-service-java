package com.ts.dbinterface.samples.document.mongodb.dao;

import com.ts.dbinterface.samples.document.mongodb.entity.MongoDBCollection;
import com.ts.dbinterface.service.document.mongodb.MongoDB;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDAOImplMongoDB implements UserDAOMongoDB {

    private MongoDB mongoDB;
    private MongoDBCollection mongoDBCollection;

    @Autowired
    public UserDAOImplMongoDB(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
        this.mongoDBCollection = new MongoDBCollection();
    }


    @Override
    public List<Document> findAll() {
        return mongoDB.findAllDocuments(mongoDBCollection.getCollectionName());
    }

    @Override
    public Document findUser(Document query) {
        return mongoDB.findDocument(
            mongoDBCollection.getCollectionName(),
            query
        );
    }

    @Override
    public boolean add(Document document) {
        return mongoDB.createDocument(
            mongoDBCollection.getCollectionName(),
            document
        );
    }

    @Override
    public boolean update(Document query, Document update) {
        return mongoDB.modifyDocument(
            mongoDBCollection.getCollectionName(),
            query,
            update
        );
    }

    @Override
    public boolean delete(Document query) {
        return mongoDB.deleteDocument(
            mongoDBCollection.getCollectionName(),
            query
        );
    }
}
