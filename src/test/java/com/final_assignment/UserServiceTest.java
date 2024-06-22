package com.final_assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.lang.model.element.Name;
import java.time.LocalDate;
import java.util.Optional;
import java.util.jar.Attributes;
import static org.assertj.core.api.ClassBasedNavigableIterableAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserService userMapper;
    
    @Test
    void findByNameStartingWith() {
    }

    @Test
    void 存在するユーザーのIDを指定したときに正常にユーザーが返されること() {
            doReturn(Optional.of(new User(1, "yamada", "yamada@example.com"))).when(userMapper).findName(1);
            User actual = userService.findName(1);
            assertThat(actual).isEqualTo(new User(1, "yamada", "yamada@example.com"));
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
