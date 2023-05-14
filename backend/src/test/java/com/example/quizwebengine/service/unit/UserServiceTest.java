package com.example.quizwebengine.service.unit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.quizwebengine.dto.UserDTO;
import com.example.quizwebengine.mapper.UserMapper;
import com.example.quizwebengine.model.user_info.Role;
import com.example.quizwebengine.model.user_info.User;
import com.example.quizwebengine.payload.request.SignupRequest;
import com.example.quizwebengine.repository.UserRepository;
import com.example.quizwebengine.service.impl.UserServiceImpl;
import java.security.Principal;
import java.util.Collections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    BCryptPasswordEncoder encoder;

    @Mock
    UserMapper userMapper;

    @InjectMocks
    UserServiceImpl service;

    @Mock
    Principal mockPrincipal;

    User user;
    UserDTO userDTO;
    SignupRequest request;

    @BeforeEach
    void setUp() {

        user = new User();
        userDTO = new UserDTO();
        request = new SignupRequest();

        user.setUsername("username");
        user.setFirstname("first");
        user.setLastname("last");
        user.setId(1L);
        user.setBio("bio");
        user.setEmail("email");

        userDTO.setFirstname(user.getFirstname());
        userDTO.setLastname(user.getLastname());
        userDTO.setBio(user.getBio());

        request.setEmail(user.getEmail());
        request.setFirstname(user.getFirstname());
        request.setLastname(user.getLastname());
        request.setUsername(user.getUsername());
        request.setPassword("password");


    }

    @Test
    void createUser() {
        when(encoder.encode(anyString())).thenReturn(request.getPassword());
        user.setId(null);
        user.setBio(null);
        user.setPassword("password");
        user.setRoles(Collections.singleton(Role.CREATOR));
        when(userRepository.save(user)).thenReturn(user);

        User retrievedUser = service.createUser(request);

        assertAll(
                () -> verify(userRepository).save(user),
                () -> assertEquals(retrievedUser, user)
        );


    }

    @Test
    void updateUser() {
        when(userRepository.save(user)).thenReturn(user);
        when(mockPrincipal.getName()).thenReturn("username");
        when(userRepository.findUserByUsername(user.getUsername())).thenReturn(java.util.Optional.ofNullable(user));
        doNothing().when(userMapper).updateUserByDto(user, userDTO);

        User retrievedUser = service.updateUser(userDTO, mockPrincipal);

        assertAll(
                () -> verify(userRepository).findUserByUsername(user.getUsername()),
                () -> verify(mockPrincipal).getName(),
                () -> verify(userRepository).save(user),
                () -> assertEquals(retrievedUser, user)
        );
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