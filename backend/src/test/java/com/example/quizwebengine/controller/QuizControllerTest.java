package com.example.quizwebengine.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.quizwebengine.payload.request.LoginRequest;
import com.example.quizwebengine.payload.request.QuizCreationRequest;
import com.example.quizwebengine.payload.request.SignupRequest;
import com.example.quizwebengine.payload.response.JWTTokenSuccessResponse;
import com.example.quizwebengine.payload.response.QuizCreationResponse;
import com.example.quizwebengine.payload.response.QuizDataResponse;
import com.example.quizwebengine.security.JWTAuthenticationFilter;
import com.example.quizwebengine.security.SecurityConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.jsonwebtoken.Jwts;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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

    @AfterAll
    static void tearDown() {
        postrgresContainer.stop();
    }

    private Long userId;

    @BeforeAll
    public static void beforeAll() {
        postrgresContainer.start();
    }

    @DisplayName("Case: creation process")
    @Nested
    class CreationTestClass{


        private final String username = "testCreationUsername";

        @BeforeEach
        public void setup() throws Exception {
            mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
            String token = getJWTToken(username).split(" ")[1];
            userId = Long.parseLong(
                (String) Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .get("id")
            );
        }


        @Test
        @WithMockUser(username = username, roles = {"USER"})
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

    @DisplayName("Case: daily quiz retrieval process")
    @Nested
    class DailyQuizRetrievalTestClass{

        private final String username = "testRetrievalUsername";

        @BeforeEach
        public void setup() throws Exception {
            mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
            String token = getJWTToken(username).split(" ")[1];
            userId = Long.parseLong(
                (String) Jwts.parser()
                    .setSigningKey(SecurityConstants.SECRET)
                    .parseClaimsJws(token)
                    .getBody()
                    .get("id")
            );
        }

        @Test
        @WithMockUser(username = username, roles = {"USER"})
        void testRetrieveDailyQuiz() throws Exception {

            Thread.sleep(5000L);
            MvcResult mvcResult = mockMvc.perform(get("/quiz/daily")
                                                      .requestAttr(JWTAuthenticationFilter.USER_ID_KEY, userId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
            QuizDataResponse quizDataResponse = (QuizDataResponse) asStringObject(
                mvcResult.getResponse().getContentAsString(),
                QuizDataResponse.class
            );
            assertEquals(10, quizDataResponse.getQuestions().size());
        }
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static Object asStringObject(final String string, Class<?> c) {
        try {
            return new ObjectMapper().readValue(string, c);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String getJWTToken(String username) throws Exception {
        Faker faker = new Faker();
        SignupRequest signupRequest = new SignupRequest();
        signupRequest.setEmail(faker.internet().emailAddress());
        signupRequest.setFirstname(faker.name().firstName());
        signupRequest.setLastname(faker.name().lastName());
        signupRequest.setUsername(username);
        String stubPassword = faker.business().creditCardNumber();
        signupRequest.setPassword(stubPassword);
        signupRequest.setConfirmPassword(stubPassword);

        mockMvc.perform(post("/api/auth/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(signupRequest)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUsername(username);
        loginRequest.setPassword(stubPassword);

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