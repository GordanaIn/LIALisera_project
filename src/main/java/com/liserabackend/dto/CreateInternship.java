package com.liserabackend.dto;

import lombok.Value;

@Value
public class CreateInternship {
    String employersName;
    String title;
    String description;
    String status;
    Boolean favorite;
}
