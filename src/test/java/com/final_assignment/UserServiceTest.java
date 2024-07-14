package com.final_assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserMapper userMapper;

    private List<User> mockUsers;

    @BeforeEach
    void setUp() {
        List<User> mockUsers = Arrays.asList(
                new User(1, "yamada", "yamada@example.com"),
                new User(2, "kobashi", "kobashi@example.com"),
                new User(3, "akiyama", "akiyama@example.com"),
                new User(4, "misawa", "misawa@example.com"),
                new User(5, "takayama", "takayama@example.com")
                );
    }
    
    @Test
    void 全レコードを取得する事() {
        doReturn(mockUsers).when(userMapper).findAll();
        List<User> actual = userService.findByNameStartingWith(null);
        assertThat(actual).isEqualTo(mockUsers);
    }

    @Test
    void 指定した名前から始まるレコードを取得できること() {
        doReturn(List.of(new User(4, "misawa", "misawa@example.com"))).when(userMapper).findByNameStartingWith("m");
        List<User> actual = userService.findByNameStartingWith("m");
        assertThat(actual).isEqualTo(List.of(new User(4, "misawa", "misawa@example.com")));
    }

    @Test
    void 指定したIDのレコードを取得できること() {
        doReturn(Optional.of(new User(1, "yamada", "yamada@example.com"))).when(userMapper).findById(1);
        User actual = userService.findById(1);
        assertThat(actual).isEqualTo(new User(1, "yamada", "yamada@example.com"));
        verify(userMapper, times(1)).findById(1);
    }

    @Test
    void 存在しないIDを指定した場合は例外が発生すること() {
        doThrow(new UserNotFoundException("user not found")).when(userMapper).findById(999);
        assertThrows(UserNotFoundException.class, () -> userService.findById(999));
        verify(userMapper, times(1)).findById(999);
    }

    @Test
    void 登録処理を実施するとその内容が期待通りであること() {
        doNothing().when(userMapper).insert(any(User.class));
        User actual = userService.insert("jake", "jake@example.com");
        assertNotNull(actual);
        assertEquals("jake", actual.getName());
        assertEquals("jake@example.com", actual.getEmail());
        verify(userMapper, times(1)).insert(any(User.class));
    }

    @Test
    void レコードが更新されること() {
        User existingUser = new User(1, "yamada", "yamada@example.com");
        when(userMapper.findById(1)).thenReturn(Optional.of(existingUser));
        doNothing().when(userMapper).update(1, "jake", "jake@example.com");
        assertDoesNotThrow(() -> userService.update(1, "jake", "jake@example.com"));
        verify(userMapper, times(1)).update(1, "jake", "jake@example.com");

    }

    @Test
    void 存在しないレコードを更新すると例外が発生すること() {
        when(userMapper.findById(1)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.update(1, "jake", "jake@example.com"));
        verify(userMapper, never()).update(1, "jake", "jake@example.com");
    }

    @Test
    void レコードが削除されること() {
        User existingUser = new User(1, "yamada", "yamada@example.com");
        when(userMapper.findById(1)).thenReturn(Optional.of(existingUser));
        doNothing().when(userMapper).delete(1);
        assertDoesNotThrow(() -> userService.delete(1));
        verify(userMapper, times(1)).delete(1);
    }

    @Test
    void 存在しないレコードを削除すると例外が発生すること() {
        when(userMapper.findById(1)).thenReturn(Optional.empty());
        assertThrows(UserNotFoundException.class, () -> userService.delete(1));
        verify(userMapper, never()).delete(1);
    }
}
