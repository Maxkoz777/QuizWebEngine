package com.example.quizwebengine.service;

import com.example.quizwebengine.dto.UserDTO;
import com.example.quizwebengine.model.user_info.User;
import com.example.quizwebengine.payload.request.SignupRequest;
import java.security.Principal;

public interface UserService {

    User createUser(SignupRequest signupRequest);

    User updateUser(UserDTO userDTO, Principal principal);

    User getCurrentUser(Principal principal);

    User getUserById(Long id);
}
