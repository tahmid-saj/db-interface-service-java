package com.ts.dbinterface.dbinterface;

import com.ts.dbinterface.service.kv.DynamoDB;
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
	public CommandLineRunner commandLineRunner(DynamoDB dynamoDB) {
		return runner -> {
//			dynamoDB.createTable("test_table", "test_pk");
//			dynamoDB.createTable("test_table_composite_key", "test_pk_1", "test_pk_2");
//			dynamoDB.listTables();
//			dynamoDB.describeTable("test_table");

//			dynamoDB.deleteTable("test_table_composite_key");

//			List<String[]> extraFields = new ArrayList<>();
//			extraFields.add(new String[]{"attribute_name_4", "attribute_value_9"});
//			extraFields.add(new String[]{"attribute_name_3", "attribute_value_9"});

//			System.out.println(dynamoDB.addItem("test_table", "test_pk", "123_val", extraFields));
//			System.out.println(dynamoDB.readItem("test_table", "test_pk", "123_val", null));
//			System.out.println(dynamoDB.updateItem("test_table", "test_pk", "123_val", extraFields));
		};
	}
}
