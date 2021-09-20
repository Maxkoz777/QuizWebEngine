package com.example.quizwebengine.service;

import com.example.quizwebengine.dto.UserDTO;
import com.example.quizwebengine.exceptions.UserExistException;
import com.example.quizwebengine.model.userInfo.Role;
import com.example.quizwebengine.model.userInfo.User;
import com.example.quizwebengine.payload.request.SignupRequest;
import com.example.quizwebengine.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User createUser(SignupRequest signupRequest) {
        User user = new User();
        user.setEmail(signupRequest.getEmail());
        user.setName(signupRequest.getFirstname());
        user.setLastname(signupRequest.getLastname());
        user.setUsername(signupRequest.getUsername());
        user.setPassword(passwordEncoder.encode(signupRequest.getPassword()));
        user.getRoles().add(Role.CREATOR);

        try {
            log.info("Saving User {}", user.getUsername());
            return userRepository.save(user);
        } catch (Exception exception) {
            log.error("Error during registration. {}", exception.getMessage());
            throw new UserExistException("The user " + " already exist. Please, check the credentials");
        }
    }

    public User updateUser(UserDTO userDTO, Principal principal) {
        User user = getUserByPrincipal(principal);
        user.setName(userDTO.getFirstname());
        user.setLastname(userDTO.getLastname());
        user.setBio(userDTO.getBio());
        return userRepository.save(user);
    }

    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

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
