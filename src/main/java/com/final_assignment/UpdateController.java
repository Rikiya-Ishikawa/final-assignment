package com.final_assignment;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateController {
    private final UpdateService updateService;

    public UpdateController(UpdateService updateService) {
        this.updateService = updateService;
    }

    @PatchMapping("/users/{id}")
    public GlobalExceptionHandler update(@PathVariable Integer id, @RequestBody UpdateRequest request) {
        boolean isUpdated = updateService.findByUpdateId(id, request.getName(), request.getEmail());

        if(isUpdated) {
            UserResponse body = new UserResponse("user updated");
            return body;
        } else {
            GlobalExceptionHandler globalExceptionHandler = new GlobalExceptionHandler();
            return globalExceptionHandler;
        }
    }
}


