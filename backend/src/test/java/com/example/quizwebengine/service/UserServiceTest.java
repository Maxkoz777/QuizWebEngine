package com.example.quizwebengine.service;

import com.example.quizwebengine.model.userInfo.User;
import com.example.quizwebengine.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.Principal;

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

    @Mock
    Principal mockPrincipal;

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
        when(mockPrincipal.getName()).thenReturn("username");
        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(java.util.Optional.ofNullable(user));

        User retrievedUser = service.getCurrentUser(mockPrincipal);

        assertAll(
                () -> verify(userRepository).findUserByUsername(user.getUsername()),
                () -> verify(mockPrincipal).getName(),
                () -> assertEquals(retrievedUser, user)
        );



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