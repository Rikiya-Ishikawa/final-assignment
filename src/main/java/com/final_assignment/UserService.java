package com.final_assignment;

import org.springframework.stereotype.Service;

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
}
