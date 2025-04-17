package main.java.com.countonme.server.repository;

import com.countonme.server.model.MongoUser;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MongoUserRepository extends MongoRepository<MongoUser, String> {
}
