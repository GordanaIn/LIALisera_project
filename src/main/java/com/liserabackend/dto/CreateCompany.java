package com.liserabackend.dto;

import lombok.Value;

@Value
public class CreateCompany {
    String name;
    String orgNumber;
    String userId;
}
