package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import java.time.LocalDate;

@Value
public class  InternshipVacancyDTO {
    String id;
    String contactEmployer;
    String title;
    String description;
    String status;
    LocalDate datePosted;
    String contactPhone;
    String duration; //how long the internship last ex- 3 months

    @JsonCreator
    public InternshipVacancyDTO(
            @JsonProperty("id") String id,
            @JsonProperty("employersName") String contactEmployer,
            @JsonProperty("title")  String title,
            @JsonProperty("description") String description,
            @JsonProperty("status") String status,
            @JsonProperty("datePosted") LocalDate datePosted,
            @JsonProperty("contactPhone")  String contactPhone,
            @JsonProperty("duration")  String duration) {
        this.id=id;
        this.contactEmployer=contactEmployer;
        this.title=title;
        this.description=description;
        this.status=status;
        this.datePosted=datePosted;
        this.contactPhone=contactPhone;
        this.duration=duration;
    }

}
