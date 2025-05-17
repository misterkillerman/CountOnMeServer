package com.example.CountOnMe.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.CountOnMe.model.User;
import com.example.CountOnMe.repository.UserRepository;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/checkUserPassword")
    public ResponseEntity<Boolean> checkUserPassword(
        @RequestParam("email") String email, 
        @RequestParam("password") String password
    ){
        User user = userRepository.findUserByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return ResponseEntity.ok(true);
        } else {
            return ResponseEntity.ok(false);
        }
    }

}
