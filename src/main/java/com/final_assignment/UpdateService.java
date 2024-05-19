package com.final_assignment;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateService {
    private final UpdateMapper updateMapper;

    public UpdateService(UpdateMapper updateMapper) {
        this.updateMapper = updateMapper;
    }

    public boolean findByUpdateId(Integer id, String name, String email) {
         Entity entity= updateMapper.findById(id);
        if(entity == null) {
            return false;
        }
        updateMapper.update(id, name, email);
        return true;
    }
}
