package com.example.quizwebengine.model.userInfo;

import lombok.Data;

@Data
public class User {

    private long id;

    private String alias;

    private String password;

    private Role role;

    private Statistics statistics;

    private ContactInfo contactInfo;

}
