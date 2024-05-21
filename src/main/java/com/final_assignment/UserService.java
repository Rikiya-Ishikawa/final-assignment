package com.final_assignment;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserMapper userMapper;
    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    public User insert(String name, String email) {
        User user = new User(null, name, email);
        userMapper.insert(user);
        return user;
    }

    public User findByUpdateId(Integer id, String name, String email) {
        Optional<User> user = UserMapper.findById(id);
        if(user.isPresent()) {
            return UserMapper.update(id, name, email);
        } else {
            throw new NameNotFoundException("name not found");
        }
    }
}
