package com.liserabackend.dto;

import lombok.Value;

import java.time.LocalDate;

@Value
public class CreateInternship {
    String userId;
    String employersName;
    String title;
    String description;
    LocalDate datePosted;
    String contactPhone;
    String duration;

}
