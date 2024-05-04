package com.final_assignment;

import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {
    private Update update;

    public UpdateService(Update update) {
        this.update = update;
    }

    public Entity updateEntity(String name, String email) {
        Entity entity = new Entity(null, name, email);
        UpdateMapper.updateEntity(entity);
        return entity;
    }
}
