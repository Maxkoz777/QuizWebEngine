package com.example.quizwebengine.security;

public class SecurityConstants {

    // url for authorization
    public static final String SIGN_UP_URLS = "/api/auth/**";

    public static final String GET_QUIZ_DATA = "/quiz/**";

    public static final String SWAGGER = "swagger-ui.html";

    public static final String SECRET = "SecretKeyJWT";

    public static final String TOKEN_PREFIX = "Bearer ";

    public static final String HEADER_STRING = "Authorization";

    public static final String CONTENT_TYPE = "application/json";

    public static final long EXPIRATION_TIME = 60_000_000;

}
