package com.ts.dbinterface.dbinterface;

import com.ts.dbinterface.service.kv.DynamoDB;
import com.ts.dbinterface.service.relational.RDS;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(
		scanBasePackages = {
				"com.ts.dbinterface"
		}
)
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(RDS rds) {
		return runner -> {
//			rds.createDBInstance("test-db-1", "db.t3.micro", "postgres", 20);
//			rds.deleteDBInstance("test-db");
			System.out.println(rds.describeDBInstance("test-db"));
//			rds.modifyDBInstance("test-db", "db.t3.micro", 20);
//			rds.rebootDBInstance("test-db");
		};
	}
}
