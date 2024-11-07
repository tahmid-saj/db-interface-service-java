package com.ts.dbinterface.samples.document.mongodb;

import com.ts.dbinterface.samples.document.documentdb.DocumentDBExample;
import com.ts.dbinterface.samples.document.mongodb.dao.UserDAOMongoDB;
import com.ts.dbinterface.samples.document.mongodb.entity.MongoDBCollection;
import com.ts.dbinterface.samples.document.mongodb.ops.MongoDBOps;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

// Uncommenting @SpringBootApplication below will run this file
//@SpringBootApplication
public class MongoDBExample {

    public static void main(String[] args) {
        SpringApplication.run(DocumentDBExample.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(MongoDBOps mongoDBOps, UserDAOMongoDB userDAOMongoDB) {
        return runner -> {
//        collection operations
            System.out.println(mongoDBOps.createCollection(new MongoDBCollection()));
            System.out.println(mongoDBOps.deleteCollection(new MongoDBCollection()));

//        document operations
            System.out.println(userDAOMongoDB.findAll());
        };
    }
}
