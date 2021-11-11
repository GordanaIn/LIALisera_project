package com.liserabackend.dto;

import lombok.Value;

@Value
public class CreateStudent {
    String firstName;
    String lastName;
    String email;
    String phone;
    String username;
    String password;
    String linkedInUrl;
    String schoolName;
}
