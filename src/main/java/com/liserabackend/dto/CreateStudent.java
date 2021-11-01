package com.liserabackend.dto;

import lombok.Value;

@Value
public class CreateStudent {
    String username;
    String email;
    String password;
    String firstName;
    String lastName;
    String phone;
    String systemStatus;
    String nameOfProfession;
    String resume;
    String video;
    String personalLetter;
    String linkedInUrl;
    String schoolName;
}
