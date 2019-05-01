package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path="/users")
public class CRUDController {
    // Automatically gets the bean from the Spring IoC
    @Autowired
    private UserRepository userRepository;

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping(path="/create")
    public @ResponseBody String createUser(@RequestBody User body) {
        User user = new User();
        user.setName(body.getName());
        user.setEmail(body.getEmail());
        userRepository.save(user);
        return "User created.";
    }

    @DeleteMapping(path="/delete")
    public @ResponseBody String deleteUser(@RequestBody String id) {
        boolean exists = userRepository.existsById(Integer.parseInt(id));
        if(!exists)
            return "That user does not exist.";

        userRepository.deleteById(Integer.parseInt(id));
        return "User deleted";
    }

    // Request body is used to deserialize the JSON into the User entity
    @PutMapping(path="/update")
    public @ResponseBody String updateUser(@RequestBody User body) {
        User user = userRepository.findById(body.getId()).orElse(null);

        if(user == null)
            return "User not found.";

        user.setName(body.getName());
        userRepository.save(user);
        return "User updated.";
    }
}