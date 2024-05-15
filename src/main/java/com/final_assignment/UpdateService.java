package com.final_assignment;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class UpdateService {
    private final UpdateMapper updateMapper;

    public UpdateService(UpdateMapper updateMapper) {
        this.updateMapper = updateMapper;
    }

    public Entity update(Integer id, UpdateRequest request) {
        Entity entity = updateMapper.update(id, request);
        if (entity == null) {
            // ユーザーが見つからない場合の処理
            return null;
        }
        return entity;
    }
}
