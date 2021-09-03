package com.example.quizwebengine.model.userInfo;

import lombok.Data;

import java.util.Date;

@Data
public class ContactInfo {

    private long id;

    private String name;

    private String surname;

    private String middleName;

    private Date dateOfBirth;

    private String email;

    private String telegram;

}
