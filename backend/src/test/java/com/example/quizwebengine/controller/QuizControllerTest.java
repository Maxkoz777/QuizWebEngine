package com.example.quizwebengine.controller;

import com.example.quizwebengine.payload.request.LoginRequest;
import com.example.quizwebengine.payload.request.QuizCreationRequest;
import com.example.quizwebengine.payload.request.SignupRequest;
import com.example.quizwebengine.payload.response.JWTTokenSuccessResponse;
import com.example.quizwebengine.payload.response.QuizCreationResponse;
import com.example.quizwebengine.security.JWTAuthenticationFilter;
import com.example.quizwebengine.security.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles({"test"})
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class QuizControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext context;

    @Container
    public static PostgreSQLContainer postrgresContainer = new PostgreSQLContainer();

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postrgresContainer::getJdbcUrl);
        registry.add("spring.datasource.password", postrgresContainer::getPassword);
        registry.add("spring.datasource.username", postrgresContainer::getUsername);
    }

    @AfterEach
    void tearDown() throws Exception {
        postrgresContainer.stop();
    }

    private String token;
    private Long userId;

    @BeforeEach
    public void setup() throws Exception {
        postrgresContainer.start();
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        token = getJWTToken().split(" ")[1];
        userId = Long.parseLong(
                (String) Jwts.parser()
                .setSigningKey(SecurityConstants.SECRET)
                .parseClaimsJws(token)
                .getBody()
                .get("id")
        );
    }

    @DisplayName("Case: creation process")
    @Nested
    class CreationTestClass{
        @Test
        @WithMockUser(username = "testuser", roles = {"USER"})
        void testCreateNewQuiz() throws Exception {
            String quizName = "Test Quiz";
            QuizCreationRequest request = new QuizCreationRequest();
            request.setName(quizName);

            MvcResult mvcResult = mockMvc.perform(post("/quiz")
                            .contentType(MediaType.APPLICATION_JSON)
                            .requestAttr(JWTAuthenticationFilter.USER_ID_KEY, userId)
                            .content(asJsonString(request)))
                    .andExpect(status().isOk())
                    .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                    .andReturn();
            QuizCreationResponse quizDataResponse = (QuizCreationResponse) asStringObject(
                    mvcResult.getResponse().getContentAsString(),
                    QuizCreationResponse.class
            );
            assertNotNull(quizDataResponse.getQuizId());
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

//    @Test
//    void getDataAboutQuiz() {
//    }
//
//    @Test
//    void updateQuizData() {
//    }
//
//    @Test
//    void deleteQuizData() {
//    }
//
//    @Test
//    void getListOfQuizzesForUser() {
//    }

    private String getJWTToken() throws Exception {
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
        return jwtTokenSuccessResponse.getToken();
    }
}