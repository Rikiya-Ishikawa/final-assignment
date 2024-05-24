package com.final_assignment;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
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
        Optional<User> user = userService.findById(id, request.getName(), request.getEmail());
        UserResponse body = new UserResponse("user updated");
        return ResponseEntity.ok(body);
    }
}
