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
}
