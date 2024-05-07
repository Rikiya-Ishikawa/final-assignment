package com.final_assignment;

import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateController {
    private final UpdateService updateService;

    public UpdateController(UpdateService updateService) {
        this.updateService = updateService;
    }

    @PatchMapping("/users/{id}")
    public void update(@RequestBody Entity entity) {
        updateService.update(entity);
    }
}


