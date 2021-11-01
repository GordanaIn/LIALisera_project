package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.liserabackend.enums.AdvertStatus;
import lombok.Value;

@Value
public class AdvertDTO {
    String id;
    String contactEmployer;
    String title;
    String description;
    String status;
    String datePosted;
    String contactPhone;
    String duration; //how long the internship last ex- 3 months

    @JsonCreator
    public AdvertDTO(
            @JsonProperty("id") String id,
            @JsonProperty("employersName") String contactEmployer,
            @JsonProperty("title")  String title,
            @JsonProperty("description") String description,
            @JsonProperty("status") String status,
            @JsonProperty("datePosted")String datePosted,
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