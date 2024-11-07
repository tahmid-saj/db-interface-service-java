package com.ts.dbinterface.rest.document.documentdb;

import com.ts.dbinterface.service.document.documentdb.DocumentDB;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RestController
@RequestMapping("/document/documentdb")
public class DocumentDBController {

    private DocumentDB documentDB;

    @Autowired
    public DocumentDBController(DocumentDB documentDB) {
        this.documentDB = documentDB;
    }

//    cluster operations
    @PostMapping("/clusters")
    public boolean createDBCluster(@RequestBody Map<String, Object> cluster) {
        String clusterName = (String)cluster.get("clusterName");

        return documentDB.createDBCluster(clusterName);
    }

    @PutMapping("/clusters")
    public boolean modifyDBCluster(@RequestBody Map<String, Object> cluster) {
        String clusterName = (String)cluster.get("clusterName");

        return documentDB.modifyDBCluster(clusterName);
    }

    @DeleteMapping("/clusters/{clusterName}")
    public boolean deleteDBCluster(@PathVariable String clusterName) {
        return documentDB.deleteDBCluster(clusterName);
    }

//    collection operations
    @PostMapping("/collections")
    public boolean createCollection(@RequestBody Map<String, Object> collection) {
        String collectionName = (String)collection.get("collectionName");

        return documentDB.createCollection(collectionName);
    }

    @DeleteMapping("/collections/{collectionName}")
    public boolean deleteCollection(@PathVariable String collectionName) {
        return documentDB.deleteCollection(collectionName);
    }

//    collection operations
    @GetMapping("/collections/{collectionName}/documents")
    public List<Document> findAllDocuments(@PathVariable String collectionName) {
        return documentDB.findAllDocuments(collectionName);
    }

    @PostMapping("/collections/{collectionName}/documents/find")
    public Document findDocument(@PathVariable String collectionName, @RequestBody Map<String, Object> queryInput) {
        Document query = (Document) queryInput.get("query");

        return documentDB.findDocument(collectionName, query);
    }

    @PostMapping("/collections/{collectionName}/documents")
    public boolean createDocument(@PathVariable String collectionName, @RequestBody Map<String, Object> documentInput) {
        Document document = (Document) documentInput.get("document");

        return documentDB.createDocument(collectionName, document);
    }

    @PutMapping("/collections/{collectionName}/documents")
    public boolean modifyDocument(@PathVariable String collectionName, @RequestBody Map<String, Object> queryInput, @RequestBody Map<String, Object> updateInput) {
        Document query = (Document) queryInput.get("query");
        Document document = (Document) updateInput.get("update");

        return documentDB.modifyDocument(collectionName, query, document);
    }

    @PutMapping("/collections/{collectionName}/documents/delete")
    public boolean deleteDocument(@PathVariable String collectionName, @RequestBody Map<String, Object> queryInput) {
        Document query = (Document) queryInput.get("query");

        return documentDB.deleteDocument(collectionName, query);
    }
}
