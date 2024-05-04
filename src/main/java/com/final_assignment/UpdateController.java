package com.final_assignment;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UpdateController {
    private final UpdateService updateService;

    public UpdateController(UpdateService updateService) {
        this.updateService = updateService;
    }

    @PutMapping("/users/{id}")
    public void updateYourEntity(@RequestBody Entity entity) {
        updateService.update(entity);
    }
}
