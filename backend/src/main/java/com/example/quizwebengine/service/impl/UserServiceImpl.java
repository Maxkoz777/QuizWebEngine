package com.example.quizwebengine.service.impl;

import com.example.quizwebengine.dto.UserDTO;
import com.example.quizwebengine.mapper.UserMapper;
import com.example.quizwebengine.model.user_info.Role;
import com.example.quizwebengine.model.user_info.User;
import com.example.quizwebengine.payload.request.SignupRequest;
import com.example.quizwebengine.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements com.example.quizwebengine.service.UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    @Override
    public User createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setFirstname(signupRequest.getFirstname());
        user.setLastname(signupRequest.getLastname());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.getRoles().add(Role.CREATOR);

        log.info("Saving User {}", user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(UserDTO userDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        userMapper.updateUserByDto(user, userDTO);
        return userRepository.save(user);
    }

    @Override
    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id)
                .orElseThrow(() -> new UsernameNotFoundException("Unable to find user with id: " + id));
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }

}
