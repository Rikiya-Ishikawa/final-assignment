package com.final_assignment;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NameService {
    private NameMapper nameMapper;
    public NameService(NameMapper nameMapper) {
        this.nameMapper = nameMapper;
    }

    public List<Name> findByNameStartingWith(String prefix) {
        // もし prefix が null ならば全件検索
        if(prefix == null) {
            return nameMapper.findAll();
        } else {
            return nameMapper.findByNameStartingWith(prefix);
        }
    }

    public Name findName(int id) {
        Optional<Name> name = nameMapper.findName(id);
        if(name.isPresent()) {
            return name.get();
        } else {
            throw new NameNotFoundException("name not found");
        }
    }
}
