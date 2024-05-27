package com.final_assignment;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findByNames(@RequestParam(required = false) String startsWith) {
        return userService.findByNameStartingWith(startsWith);
    }


    @GetMapping("/users/{id}")
    public User findName(@PathVariable("id") int id) {
        return userService.findName(id);
    }
    @PostMapping("/users")
    public ResponseEntity<UserResponse> insert(@RequestBody UserRequest userRequest, UriComponentsBuilder uriBuilder) {
        User user = userService.insert(userRequest.getName(), userRequest.getEmail());
        URI location = uriBuilder.path("/users/{id}").buildAndExpand(user.getId()).toUri();
        UserResponse body = new UserResponse("user created");
        return ResponseEntity.created(location).body(body);
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<UserResponse> update(@PathVariable Integer id, @RequestBody UserRequest request) {
        userService.update(id, request.getName(), request.getEmail());
        UserResponse response = new UserResponse("user updated");
        return ResponseEntity.ok(response);
    }

}
