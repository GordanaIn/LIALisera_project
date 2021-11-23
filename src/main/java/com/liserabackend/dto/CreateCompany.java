package com.liserabackend.dto;

import lombok.Value;

@Value
public class CreateCompany {
    String name;
    String organizationNumber;
    String companyEmail;
    String userEmail;
    String username;
    String password;
}
