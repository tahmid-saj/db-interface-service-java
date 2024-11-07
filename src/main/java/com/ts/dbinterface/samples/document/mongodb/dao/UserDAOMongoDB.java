package com.ts.dbinterface.samples.document.mongodb.dao;

import org.bson.Document;

import java.util.List;

public interface UserDAOMongoDB {

    List<Document> findAll();

    Document findUser(Document query);

    boolean add(Document document);

    boolean update(Document query, Document update);

    boolean delete(Document query);
}
