package com.ts.dbinterface.samples.kv.dynamodb;

import com.ts.dbinterface.samples.kv.dynamodb.dao.UserDAO;
import com.ts.dbinterface.samples.kv.dynamodb.entity.DynamoDBTable;
import com.ts.dbinterface.samples.kv.dynamodb.entity.User;
import com.ts.dbinterface.samples.kv.dynamodb.ops.DynamoDBOps;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

// Uncommenting @SpringBootApplication below will run this file
//@SpringBootApplication
public class DynamoDBExample {

    public static void main(String[] args) {
        SpringApplication.run(DynamoDBExample.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(DynamoDBOps dynamoDBOps, UserDAO userDAO) {
        return runner -> {
//            table operations
            System.out.println(dynamoDBOps.createTable(new DynamoDBTable(
                    "default-users-table",
                    "userID",
                    2L,
                    2L
            )));
            System.out.println(dynamoDBOps.listTables());
            System.out.println(dynamoDBOps.describeTable(new DynamoDBTable(
                    "default-users-table",
                    "userID",
                    2L,
                    2L
            )));
            System.out.println(dynamoDBOps.updateTable(new DynamoDBTable(
                    "default-users-table",
                    "userID",
                    2L,
                    2L
            )));
            System.out.println(dynamoDBOps.deleteTable(new DynamoDBTable(
                    "default-users-table",
                    "userID",
                    2L,
                    2L
            )));

//            item operations
            System.out.println(userDAO.findAll());
            System.out.println(userDAO.findById("123"));
            System.out.println(userDAO.add(new User("123", "John", "Doe")));
            System.out.println(userDAO.update(new User("123", "John", "Smith")));
            System.out.println(userDAO.delete("123"));
        };
    }
}
