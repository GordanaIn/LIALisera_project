package com.liserabackend.dto;

import lombok.Value;

@Value
public class CreateCompany {
    String name;
    String orgNumber;
    String companyEmail;
  //  String userEmail;
    String userId;
 //   String password;
}
