package lab.webpost.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lab.webpost.domain.User;

@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;


    //TODO: end point for validate user by username 
    @GetMapping("/users/{username}")
    public ResponseEntity getUserByUsername(@PathVariable String username) {
        List<User> users = userRepository.findByUsername(username);
        if(users.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found");
        }
        User user = users.get(0);
       return ResponseEntity.ok().body(user);
    }
}
