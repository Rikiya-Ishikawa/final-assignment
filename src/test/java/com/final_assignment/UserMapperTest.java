package com.final_assignment;

import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;

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
        Optional<User> actual = userMapper.findById(1);
        assertThat(actual.get()).isEqualTo(new User(1, "jake", "jake@example.com"));

    }

    @Test
    void 存在しないIDのを指定した場合は空のOptionalが返ること() {
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
