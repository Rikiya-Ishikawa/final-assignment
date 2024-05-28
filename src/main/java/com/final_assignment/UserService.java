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

    public List<User> findByNameStartingWith(String prefix) {
        // もし prefix が null ならば全件検索
        if(prefix == null) {
            return userMapper.findAll();
        } else {
            return userMapper.findByNameStartingWith(prefix);
        }
    }

    public User findName(int id) {
        Optional<User> name = userMapper.findName(id);
        if(name.isPresent()) {
            return name.get();
        } else {
            throw new NameNotFoundException("name not found");
        }
    }

    public User insert(String name, String email) {
        User user = new User(null, name, email);
        userMapper.insert(user);
        return user;
    }

    public void update(Integer id, String name, String email) {
        Optional<User> user = userMapper.findName(id);
        if(user.isPresent()) {
            userMapper.update(id, name, email);
        } else {
            throw new NameNotFoundException("name not found");
        }
    }

    public void delete(Integer id) {
        Optional<User> user = userMapper.findName(id);
        if(user.isPresent()) {
            userMapper.delete(id);
        } else {
            throw new NameNotFoundException("name not found");
        }
    }
}
