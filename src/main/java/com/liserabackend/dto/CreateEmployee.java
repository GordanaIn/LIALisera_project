package com.liserabackend.dto;

import lombok.Value;

@Value
public class CreateEmployee {
    String firstName;
    String lastName;
    String company;
    String email;
    String password;
}
