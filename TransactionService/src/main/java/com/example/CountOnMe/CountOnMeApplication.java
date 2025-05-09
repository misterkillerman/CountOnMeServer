package com.example.CountOnMe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class CountOnMeApplication {

	public static void main(String[] args) {
		SpringApplication.run(CountOnMeApplication.class, args);
	}

}
