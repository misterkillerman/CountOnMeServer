package main.java.com.countonme.server.service;

import com.countonme.server.model.MongoUser;
import com.countonme.server.repository.MongoUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final MongoUserRepository mongoRepo;
    private final MySQLUserRepository mysqlRepo;

    public UserService(MongoUserRepository mongoRepo) {
        this.mongoRepo = mongoRepo;
    }

    public List<MongoUser> getAllMongoUsers() {
        return mongoRepo.findAll();
    }

    public MongoUser createMongoUser(MongoUser user) {
        return mongoRepo.save(user);
    }
}
