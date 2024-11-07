package com.ts.dbinterface.samples.relational.rds;

import com.ts.dbinterface.samples.relational.rds.entity.RDSDBInstance;
import com.ts.dbinterface.samples.relational.rds.ops.RDSDBOps;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

// Uncommenting @SpringBootApplication below will run this file
//@SpringBootApplication
public class RDSDBExample {

    public static void main(String[] args) {
        SpringApplication.run(RDSDBExample.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(RDSDBOps rdsdbOps) {
        return runner -> {
//            db instance operations
            System.out.println(rdsdbOps.createDBInstance(new RDSDBInstance(
                "test-db",
                "db.t3.micro",
                "postgres",
                20
            )));

            System.out.println(rdsdbOps.deleteDBInstance(new RDSDBInstance(
                "test-db",
                "db.t3.micro",
                "postgres",
                20
            )));

            System.out.println(rdsdbOps.describeDBInstance(new RDSDBInstance(
                    "test-db",
                    "db.t3.micro",
                    "postgres",
                    20
            )));

            System.out.println(rdsdbOps.modifyDBInstance(new RDSDBInstance(
                    "test-db",
                    "db.t3.micro",
                    "postgres",
                    20
            )));

            System.out.println(rdsdbOps.rebootDBInstance(new RDSDBInstance(
                    "test-db",
                    "db.t3.micro",
                    "postgres",
                    20
            )));
        };
    }
}
