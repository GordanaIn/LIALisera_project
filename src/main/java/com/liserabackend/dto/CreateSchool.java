package com.liserabackend.dto;

import lombok.Value;

@Value
public class CreateSchool {
    String name;
    String organizationNumber;
    String schoolEmail;
    String phone;
    String userEmail;
    String username;
    String password;

}
