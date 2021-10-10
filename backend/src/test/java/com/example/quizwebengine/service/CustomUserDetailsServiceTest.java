package com.example.quizwebengine.service;

import com.example.quizwebengine.model.userInfo.User;
import com.example.quizwebengine.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomUserDetailsServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    CustomUserDetailsService service;

    User user;

    @BeforeEach
    void setUp() {

        user = new User();
        user.setUsername("username");
        user.setName("first");
        user.setLastname("last");
        user.setId(1L);
        user.setBio("bio");
        user.setEmail("email");

    }

    @Test
    void loadUserByUsername() {
        when(userRepository.findUserByEmail(user.getUsername())).thenReturn(java.util.Optional.ofNullable(user));

        UserDetails userDetails = service.loadUserByUsername(user.getUsername());

        assertAll(
                () -> verify(userRepository).findUserByEmail(anyString()),
                () -> assertEquals(userDetails, user)
        );

    }

    @Test
    void loadUserById() {
    }

    @Test
    void build() {
    }
}