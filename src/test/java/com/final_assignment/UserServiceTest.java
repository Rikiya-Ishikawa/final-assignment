package com.final_assignment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Optional;
import static org.mockito.Mockito.doReturn;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;
    
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
    void 存在しないユーザーのIDを指定したときにnameNotFoundが返されること() {
        doThrow(new NameNotFoundException("name not found")).when(userMapper).findName(999);
        User actual = userService.findName(999);
        assertThat(actual).isEqualTo("name not found");

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
