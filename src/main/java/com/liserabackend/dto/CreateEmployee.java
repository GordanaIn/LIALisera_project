package com.liserabackend.dto;

import lombok.Value;

@Value
public class CreateEmployee {
    String firstName;
    String lastName;
    String email;
    String username;
    String password;
    String userId;
}
