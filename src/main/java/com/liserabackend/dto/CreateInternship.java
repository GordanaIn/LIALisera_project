package com.liserabackend.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class CreateInternship {
    String userId;
    String employerName;
    String title;
    String description;
    LocalDate datePosted;
    String duration;
    int requiredNumber;
}
