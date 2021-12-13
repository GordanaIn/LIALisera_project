package com.liserabackend.dto;

import lombok.Value;

@Value
public class CreateStudent {
    String firstName;
    String lastName;
    String username;
    String phone;
    String password;
    String schoolName;
}
