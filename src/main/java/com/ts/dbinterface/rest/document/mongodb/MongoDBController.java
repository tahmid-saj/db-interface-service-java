package com.ts.dbinterface.rest.document.mongodb;

import com.ts.dbinterface.service.document.mongodb.MongoDB;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RestController
@RequestMapping("/document/mongodb")
public class MongoDBController {

    private MongoDB mongoDB;

    @Autowired
    public MongoDBController(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

//    collection operations
    @PostMapping("/collections/{collectionName}")
    public boolean createCollection(@PathVariable String collectionName) {
        return mongoDB.createCollection(collectionName);
    }

    @DeleteMapping("/collections/{collectionName}")
    public boolean deleteCollection(@PathVariable String collectionName) {
        return mongoDB.deleteCollection(collectionName);
    }

//    document operations
    @GetMapping("/collections/{collectionName}/documents")
    public List<Document> findAllDocuments(@PathVariable String collectionName) {
        return mongoDB.findAllDocuments(collectionName);
    }

    @PostMapping("/collections/{collectionName}/documents/find")
    public Document findDocument(@PathVariable String collectionName, @RequestBody Map<String, Object> queryInput) {
        Document query = (Document) queryInput.get("query");

        return mongoDB.findDocument(collectionName, query);
    }

    @PostMapping("/collections/{collectionName}/documents")
    public boolean createDocument(@PathVariable String collectionName, @RequestBody Map<String, Object> documentInput) {
        Document document = (Document) documentInput.get("document");

        return mongoDB.createDocument(collectionName, document);
    }

    @PutMapping("/collections/{collectionName}/documents")
    public boolean modifyDocument(@PathVariable String collectionName, @RequestBody Map<String, Object> queryInput, @RequestBody Map<String, Object> updateInput) {
        Document query = (Document) queryInput.get("query");
        Document document = (Document) updateInput.get("update");

        return mongoDB.modifyDocument(collectionName, query, document);
    }

    @PutMapping("/collections/{collectionName}/documents/delete")
    public boolean deleteDocument(@PathVariable String collectionName, @RequestBody Map<String, Object> queryInput) {
        Document query = (Document) queryInput.get("query");

        return mongoDB.deleteDocument(collectionName, query);
    }
}
