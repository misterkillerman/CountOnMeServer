package main.java.com.countonme.server.controller;

import com.countonme.server.model.MongoUser;
import com.countonme.server.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping("/mongo")
    public List<MongoUser> getMongoUsers() {
        return service.getAllMongoUsers();
    }

    @PostMapping("/mongo")
    public MongoUser addMongoUser(@RequestBody MongoUser user) {
        return service.createMongoUser(user);
    }
}
