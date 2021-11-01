package com.liserabackend.dto;

import lombok.Value;

@Value
public class StudentDTO {
    String studentId;
    String firstName;
    String lastName;
    String username;
    String email;
    String userId;
    String phone;
    String schoolName;

    String resume;
    String personalLetter;
    String linkedInUrl;

    //List<Document> documents;
    //List<String> roles;
    String role;
    String systemStatus;
    //List<Long> professions;

}
