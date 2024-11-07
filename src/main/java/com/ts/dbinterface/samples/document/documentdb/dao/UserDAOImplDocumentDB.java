package com.ts.dbinterface.samples.document.documentdb.dao;

import com.ts.dbinterface.samples.document.documentdb.entity.DocumentDBCollection;
import com.ts.dbinterface.service.document.documentdb.DocumentDB;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDAOImplDocumentDB implements UserDAODocumentDB {

    private DocumentDB documentDB;
    private DocumentDBCollection documentDBCollection;

    @Autowired
    public UserDAOImplDocumentDB(DocumentDB documentDB) {
        this.documentDB = documentDB;
        this.documentDBCollection = new DocumentDBCollection();
    }


    @Override
    public List<Document> findAll() {
        return documentDB.findAllDocuments(documentDBCollection.getCollectionName());
    }

    @Override
    public Document findUser(Document query) {
        return documentDB.findDocument(
            documentDBCollection.getCollectionName(),
            query
        );
    }

    @Override
    public boolean add(Document document) {
        return documentDB.createDocument(
            documentDBCollection.getCollectionName(),
            document
        );
    }

    @Override
    public boolean update(Document query, Document update) {
        return documentDB.modifyDocument(
            documentDBCollection.getCollectionName(),
            query,
            update
        );
    }

    @Override
    public boolean delete(Document query) {
        return documentDB.deleteDocument(
            documentDBCollection.getCollectionName(),
            query
        );
    }

}
