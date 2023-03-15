package com.example.quizwebengine.controller;

import com.example.quizwebengine.payload.request.LoginRequest;
import com.example.quizwebengine.payload.request.SignupRequest;
import com.example.quizwebengine.payload.response.JWTTokenSuccessResponse;
import com.example.quizwebengine.payload.response.MessageResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"test", "h2"})
@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
    }

    @DisplayName("Case: sign up process")
    @Nested
    class SignUpTestClass {
        @DirtiesContext
        @DisplayName("Case: registering is successful")
        @Test
        void registerUser01() throws Exception {
            SignupRequest signupRequest = new SignupRequest();
            signupRequest.setEmail("test@test.com");
            signupRequest.setFirstname("Test");
            signupRequest.setLastname("Testing");
            signupRequest.setUsername("testuser");
            signupRequest.setPassword("testpassword");
            signupRequest.setConfirmPassword("testpassword");

            MvcResult mvcResult = mockMvc.perform(post("/api/auth/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(signupRequest)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
            MessageResponse messageResponse = (MessageResponse) asStringObject(
                    mvcResult.getResponse().getContentAsString(),
                    MessageResponse.class
            );
            assertEquals("User registered successfully", messageResponse.getMessage());
        }

        @DirtiesContext
        @DisplayName("Case: passwords are not matched")
        @Test
        void registerUser02() throws Exception {
            SignupRequest signupRequest = new SignupRequest();
            signupRequest.setEmail("test@test.com");
            signupRequest.setFirstname("Test");
            signupRequest.setLastname("Testing");
            signupRequest.setUsername("testuser");
            signupRequest.setPassword("password");
            signupRequest.setConfirmPassword("testpassword");

            mockMvc.perform(post("/api/auth/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(signupRequest)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.PasswordMatches").value("Password doesn't match"));
        }

        @DirtiesContext
        @DisplayName("Case: email has wrong format")
        @Test
        void registerUser03() throws Exception {
            SignupRequest signupRequest = new SignupRequest();
            signupRequest.setEmail("test");
            signupRequest.setFirstname("Test");
            signupRequest.setLastname("Testing");
            signupRequest.setUsername("testuser");
            signupRequest.setPassword("testpassword");
            signupRequest.setConfirmPassword("testpassword");

            mockMvc.perform(post("/api/auth/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(signupRequest)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.Email").value("Should be in email format"));
        }
    }

    @DisplayName("Case: sign in process")
    @Nested
    class SignInTestClass {
        @DirtiesContext
        @DisplayName("Case: authentication is successful")
        @Test
        void authenticateUser01() throws Exception {
            SignupRequest signupRequest = new SignupRequest();
            signupRequest.setEmail("test@test.com");
            signupRequest.setFirstname("Test");
            signupRequest.setLastname("Testing");
            signupRequest.setUsername("testuser");
            signupRequest.setPassword("testpassword");
            signupRequest.setConfirmPassword("testpassword");

            mockMvc.perform(post("/api/auth/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(signupRequest)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsername("testuser");
            loginRequest.setPassword("testpassword");

            MvcResult mvcResult = mockMvc.perform(post("/api/auth/signin")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(loginRequest)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
            JWTTokenSuccessResponse jwtTokenSuccessResponse = (JWTTokenSuccessResponse) asStringObject(
                    mvcResult.getResponse().getContentAsString(),
                    JWTTokenSuccessResponse.class
            );
            assertTrue(jwtTokenSuccessResponse.isSuccess());
        }

        @DirtiesContext
        @DisplayName("Case: authentication is unsuccessful")
        @Test
        void authenticateUser02() throws Exception {
            SignupRequest signupRequest = new SignupRequest();
            signupRequest.setEmail("test@test.com");
            signupRequest.setFirstname("Test");
            signupRequest.setLastname("Testing");
            signupRequest.setUsername("testuser");
            signupRequest.setPassword("testpassword");
            signupRequest.setConfirmPassword("testpassword");

            mockMvc.perform(post("/api/auth/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(signupRequest)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setUsername("testuser");
            loginRequest.setPassword("incorrectpassword");

            mockMvc.perform(post("/api/auth/signin")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(loginRequest)))
                    .andExpect(status().isUnauthorized())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.password").value("Invalid password"));
        }

        @DirtiesContext
        @DisplayName("Case: username is empty")
        @Test
        void authenticateUser03() throws Exception {
            SignupRequest signupRequest = new SignupRequest();
            signupRequest.setEmail("test@test.com");
            signupRequest.setFirstname("Test");
            signupRequest.setLastname("Testing");
            signupRequest.setUsername("testuser");
            signupRequest.setPassword("testpassword");
            signupRequest.setConfirmPassword("testpassword");

            mockMvc.perform(post("/api/auth/signup")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(signupRequest)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON));

            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setPassword("incorrectpassword");

            mockMvc.perform(post("/api/auth/signin")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(asJsonString(loginRequest)))
                    .andExpect(status().isBadRequest())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andExpect(jsonPath("$.username").value("Username can't be empty"));
        }
    }


    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object asStringObject(final String string, Class c) {
        try {
            return new ObjectMapper().readValue(string, c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}