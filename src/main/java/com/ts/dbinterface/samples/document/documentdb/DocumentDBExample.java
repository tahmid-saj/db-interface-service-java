package com.ts.dbinterface.samples.document.documentdb;

import com.ts.dbinterface.samples.document.documentdb.dao.UserDAODocumentDB;
import com.ts.dbinterface.samples.document.documentdb.entity.DocumentDBCluster;
import com.ts.dbinterface.samples.document.documentdb.entity.DocumentDBCollection;
import com.ts.dbinterface.samples.document.documentdb.ops.DocumentDBOps;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

// Uncommenting @SpringBootApplication below will run this file
//@SpringBootApplication
public class DocumentDBExample {

    public static void main(String[] args) {
        SpringApplication.run(DocumentDBExample.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(DocumentDBOps documentDBOps, UserDAODocumentDB userDAO) {
        return runner -> {
//            cluster operations
            System.out.println(documentDBOps.createDBCluster(new DocumentDBCluster()));
            System.out.println(documentDBOps.modifyDBCluster(new DocumentDBCluster()));
            System.out.println(documentDBOps.deleteDBCluster(new DocumentDBCluster()));

//            collection operations
            System.out.println(documentDBOps.createCollection(new DocumentDBCollection()));
            System.out.println(documentDBOps.deleteCollection(new DocumentDBCollection()));

//            document operations
            System.out.println(userDAO.findAll());
        };
    }
}
