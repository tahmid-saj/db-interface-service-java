package com.ts.dbinterface.samples.document.documentdb.ops;

import com.ts.dbinterface.samples.document.documentdb.entity.DocumentDBCluster;
import com.ts.dbinterface.samples.document.documentdb.entity.DocumentDBCollection;
import com.ts.dbinterface.service.document.documentdb.DocumentDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentDBOps {

    private DocumentDB documentDB;

    @Autowired
    public DocumentDBOps(DocumentDB documentDB) {
        this.documentDB = documentDB;
    }

//    cluster operations
    public boolean createDBCluster(DocumentDBCluster documentDBCluster) {
        return documentDB.createDBCluster(documentDBCluster.getClusterName());
    }

    public boolean modifyDBCluster(DocumentDBCluster documentDBCluster) {
        return documentDB.modifyDBCluster(documentDBCluster.getClusterName());
    }

    public boolean deleteDBCluster(DocumentDBCluster documentDBCluster) {
        return documentDB.deleteDBCluster(documentDBCluster.getClusterName());
    }

//    collection operations
    public boolean createCollection(DocumentDBCollection documentDBCollection) {
        return documentDB.createCollection(documentDBCollection.getCollectionName());
    }

    public boolean deleteCollection(DocumentDBCollection documentDBCollection) {
        return documentDB.deleteCollection(documentDBCollection.getCollectionName());
    }
}
