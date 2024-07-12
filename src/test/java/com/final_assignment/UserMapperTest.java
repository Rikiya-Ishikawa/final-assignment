package com.final_assignment;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    void findAll() {
    }

    @Test
    void findByNameStartingWith() {
    }

    @Test
    @Sql(
            scripts = {"classpath:/sqlannotation/delete-users.sql", "classpath:/sqlannotation/insert-users.sql"},
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void 指定したIDのユーザを取得出来ること() {
        User expectedUser = new User(1, "jake", "jake@example.com");
        Optional<User> actual = userMapper.findById(1);
        assertTrue(actual.isPresent());
        actual.ifPresent(user -> {
            assertEquals(expectedUser.getId(), user.getId());
            assertEquals(expectedUser.getName(), user.getName());
            assertEquals(expectedUser.getEmail(), user.getEmail());
        });
    }

    @Test
    void 存在しないIDのを指定した場合は空のOptionalが返ること() {
        Optional<User> actual = userMapper.findById(999);
        assertFalse(actual.isPresent());
    }

    @Test
    void insert() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}
