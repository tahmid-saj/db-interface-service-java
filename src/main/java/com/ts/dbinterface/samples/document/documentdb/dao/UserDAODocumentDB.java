package com.ts.dbinterface.samples.document.documentdb.dao;

import org.bson.Document;

import java.util.List;

public interface UserDAODocumentDB {

    List<Document> findAll();

    Document findUser(Document query);

    boolean add(Document document);

    boolean update(Document query, Document update);

    boolean delete(Document query);
}
