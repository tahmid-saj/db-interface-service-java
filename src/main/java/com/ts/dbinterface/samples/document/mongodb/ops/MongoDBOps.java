package com.ts.dbinterface.samples.document.mongodb.ops;

import com.ts.dbinterface.samples.document.mongodb.entity.MongoDBCollection;
import com.ts.dbinterface.service.document.mongodb.MongoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MongoDBOps {

    private MongoDB mongoDB;

    @Autowired
    public MongoDBOps(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }

    public boolean createCollection(MongoDBCollection mongoDBCollection) {
        return mongoDB.createCollection(mongoDBCollection.getCollectionName());
    }

    public boolean deleteCollection(MongoDBCollection mongoDBCollection) {
        return mongoDB.deleteCollection(mongoDBCollection.getCollectionName());
    }
}
