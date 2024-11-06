package com.ts.dbinterface.dbinterface;

import com.ts.dbinterface.dao.kv.DynamoDB;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
	public CommandLineRunner commandLineRunner(DynamoDB dynamoDB) {
		return runner -> {
			dynamoDB.createTable("test_table", "test_pk");
		};
	}
}
