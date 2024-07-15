package com.final_assignment;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@MybatisTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserMapperTest {

    @Autowired
    UserMapper userMapper;

    @Test
    @Sql(
        scripts = {"classpath:/sqlannotation/delete-users.sql", "classpath:/sqlannotation/insert-users.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void 全件取得できること() {
        List<User> actual = userMapper.findAll();
        List<User> expected = Arrays.asList(
            new User(1, "jake", "jake@example.com"),
            new User(2, "keno", "keno@example.com"),
            new User(3, "wagnerJr", "wagnerJr@example.com")
        );
        assertEquals(expected, actual);
    }

    @Test
    @Sql(
        scripts = {"classpath:/sqlannotation/delete-users.sql", "classpath:/sqlannotation/insert-users.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void  指定した文字で始まるユーザを取得できること() {
        List<User> actual = userMapper.findByNameStartingWith("j");
        assertEquals(1, actual.get(0).getId());
        assertEquals("jake", actual.get(0).getName());
        assertEquals("jake@example.com", actual.get(0).getEmail());
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
    @Sql(
        scripts = {"classpath:/sqlannotation/delete-users.sql", "classpath:/sqlannotation/insert-users.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void 存在しないIDのを指定した場合は空のOptionalが返ること() {
        Optional<User> actual = userMapper.findById(999);
        assertFalse(actual.isPresent());
    }

    @Test
    @Sql(
        scripts = {"classpath:/sqlannotation/delete-users.sql", "classpath:/sqlannotation/insert-users.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void 新規登録ができること() {
        User newUser = new User("soya", "soya@example.com");
        userMapper.insert(newUser);
        List<User> users = userMapper.findAll();
        assertEquals(4, users.size());
        assertTrue(users.stream().anyMatch(user -> "soya".equals(user.getName()) && "soya@example.com".equals(user.getEmail())));
    }

    @Test
    @Sql(
        scripts = {"classpath:/sqlannotation/delete-users.sql", "classpath:/sqlannotation/insert-users.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void 更新処理が出来ること() {
        Optional<User> user = userMapper.findById(1);
        assertTrue(user.isPresent());
        assertDoesNotThrow(() -> userMapper.update(1, "yoichi", "yoichi@example.com"));
        List<User> users = userMapper.findAll();
        assertTrue(users.stream().anyMatch(u -> "yoichi".equals(u.getName()) && "yoichi@example.com".equals(u.getEmail())));
    }

    @Test
    @Sql(
        scripts = {"classpath:/sqlannotation/delete-users.sql", "classpath:/sqlannotation/insert-users.sql"},
        executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Transactional
    void delete() {
    }
}
