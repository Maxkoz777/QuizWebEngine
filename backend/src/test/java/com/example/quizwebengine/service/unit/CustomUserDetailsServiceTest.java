package com.example.quizwebengine.service.unit;

import com.example.quizwebengine.model.user_info.User;
import com.example.quizwebengine.repository.UserRepository;
import com.example.quizwebengine.service.impl.CustomUserDetailsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
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
        user.setFirstname("first");
        user.setLastname("last");
        user.setId(1L);
        user.setBio("bio");
        user.setEmail("email");

    }

    @Test
    void loadUserByUsername() {
        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(java.util.Optional.ofNullable(user));

        UserDetails userDetails = service.loadUserByUsername(user.getUsername());

        assertAll(
                () -> verify(userRepository).findUserByUsername(anyString()),
                () -> assertEquals(userDetails, user)
        );
    }

    @Test
    void loadUserById() {
        when(userRepository.findUserById(user.getId())).thenReturn(java.util.Optional.ofNullable(user));

        User retrievedUser = service.loadUserById(user.getId());

        assertAll(
                () -> verify(userRepository).findUserById(anyLong()),
                () -> assertEquals(retrievedUser, user)
        );
    }

    @Test
    void build() {
        assertEquals(CustomUserDetailsService.build(user), user);
    }
}