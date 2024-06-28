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
import static org.junit.jupiter.api.Assertions.*;
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
                new User(1, "kobashi", "kobashi@example.com"),
                new User(1, "akiyama", "akiyama@example.com"),
                new User(1, "misawa", "misawa@example.com"),
                new User(1, "takayama", "takayama@example.com")
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
        doThrow(new NameNotFoundException("name not found")).when(userMapper).findById(999);
        assertThrows(NameNotFoundException.class, () -> userService.findById(999));
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
    void 更新処理の実行すると引数で渡した値に変更されること() {
    }

    @Test
    void 更新処理の実行結果が例外を発生すること() {
    }

    @Test
    void 処理を実行すると引数で指定したIdのレコードが削除されること() {
    }

    @Test
    void 引数で指定したIdが存在しない時に削除処理を実行すると例外が発生すること() {
    }
}
