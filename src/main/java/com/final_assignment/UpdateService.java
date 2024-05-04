package com.final_assignment;

import org.springframework.stereotype.Service;

@Service
public class UpdateService {
    private final UpdateMapper updateMapper;

    public UpdateService(UpdateMapper entityMapper) {
        this.updateMapper = entityMapper;
    }

    public void update(Entity entity) {
        updateMapper.update(entity);
    }
}
