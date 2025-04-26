package com.example.CountOnMe;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.CountOnMe.model.User;
import com.example.CountOnMe.repository.UserRepository;

@SpringBootApplication
@EnableMongoRepositories
public class CountOnMeApplication {

    @Autowired
    public static UserRepository userRepository;

    public static List<User> users = new ArrayList<User>();

	public static void main(String[] args) {
		SpringApplication.run(CountOnMeApplication.class, args);
	}

    public void run(String... args) {
        getAllUsers();
    }

    private static void getAllUsers() {
        users = userRepository.findAll();
        users.forEach(user -> System.out.println(user.toString()));
    }

}
