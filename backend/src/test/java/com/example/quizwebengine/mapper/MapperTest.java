package com.example.quizwebengine.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.example.quizwebengine.dto.UserDTO;
import com.example.quizwebengine.model.user_info.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {UserMapperImpl.class})

public class MapperTest {

    @Autowired
    private UserMapper userMapper;

    @Nested
    @DisplayName("User mapper tests")
    class userMapperTests {

        User user;
        UserDTO userDTO;

        @BeforeEach
        void beforeEach() {
            user = new User();
            user.setId(null);
            user.setUsername("userName");
            user.setFirstname("firstName");
            user.setLastname("lastName");
            user.setBio("bio");
        }

        @Test
        @DisplayName("User to userDto test")
        void userToUserDto() {
            userDTO = userMapper.userToUserDto(user);
            assertEquals("firstName", userDTO.getFirstname());
        }

        @Test
        @DisplayName("User update by dto test")
        void userUpdateByDto() {
            userDTO = new UserDTO();
            userDTO.setUsername("userNameUpdated");
            userDTO.setLastname("lastNameUpdated");

            userMapper.updateUserByDto(user, userDTO);
            assertAll(
                () -> assertEquals("firstName", user.getFirstname()),
                () -> assertEquals("userNameUpdated", user.getUsername()),
                () -> assertEquals("lastNameUpdated", user.getLastname()),
                () -> assertEquals("bio", user.getBio())
            );
        }

    }

}
