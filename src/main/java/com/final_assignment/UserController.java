package com.final_assignment;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;

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
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody UpdateRequest request) {
        boolean isUpdated = updateService.findByUpdateId(id, request.getName(), request.getEmail());

        if(isUpdated) {
            return ResponseEntity.ok(Collections.singletonMap("message", "user updated"));
        } else {
            return new ResponseEntity<>(NameNotFoundException e, HttpServletRequest request);
        }
    }

}
