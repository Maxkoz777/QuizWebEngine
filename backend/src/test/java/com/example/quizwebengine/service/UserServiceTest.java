package com.example.quizwebengine.service;

import com.example.quizwebengine.model.userInfo.User;
import com.example.quizwebengine.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@Disabled
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService service;

    User user;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setUsername("username");
        user.setName("first");
        user.setLastname("last");
        user.setId(1L);
        user.setBio("bio");

    }

    @Test
    void createUser() {
    }

    @Test
    void updateUser() {
    }

    @Test
    void getCurrentUser() {
    }

    @Test
    void getUserById() {
        when(userRepository.findUserById(anyLong())).thenReturn(java.util.Optional.ofNullable(user));

        User retrievedUser = service.getUserById(user.getId());

        assertAll(
                () -> verify(userRepository).findUserById(user.getId()),
                () -> assertEquals(retrievedUser, user)
        );

    }
}