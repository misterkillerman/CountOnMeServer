package main.java.com.countonme.server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "mongo_users")
public class MongoUser {
    @Id
    private String id;
    private String name;

    // Getters and Setters
}
