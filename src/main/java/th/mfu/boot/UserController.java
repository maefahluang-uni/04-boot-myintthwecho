package th.mfu.boot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    public static HashMap<String, User> users = new HashMap<String, User>();

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        String username = user.getUsername();

        if (users.containsKey(username)) {
            return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT); // 409
        }

        users.put(username, user);
        return new ResponseEntity<>("User registered successfully", HttpStatus.OK); // 201
    }

    @GetMapping
    public ResponseEntity<Collection<User>> list() {
        return new ResponseEntity<>(users.values(), HttpStatus.OK); // 200
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUser(@PathVariable String username) {
        User user = users.get(username);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 404
        }

        return new ResponseEntity<>(user, HttpStatus.OK); // 200
    }

}
