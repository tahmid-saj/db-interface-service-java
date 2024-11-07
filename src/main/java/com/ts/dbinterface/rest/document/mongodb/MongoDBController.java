package com.ts.dbinterface.rest.document.mongodb;

import com.ts.dbinterface.service.document.mongodb.MongoDB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@RestController
@RequestMapping("/document/mongodb")
public class MongoDBController {

    private MongoDB mongoDB;

    @Autowired
    public MongoDBController(MongoDB mongoDB) {
        this.mongoDB = mongoDB;
    }


}
