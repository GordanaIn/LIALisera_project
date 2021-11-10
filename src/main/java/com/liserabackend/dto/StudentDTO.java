package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.liserabackend.entity.Education;
import lombok.Value;

import java.util.List;

@Value
public class StudentDTO {
    String studentId;
    String firstName;
    String lastName;
    String userId;
    String username;
    String email;
    String phone;
    String role;
    String eductionTitle;
    String schoolName;
    String educationType;
    //List<Education> educationList;
   /* String resume;
    String personalLetter;
    String linkedInUrl;*/
    //String systemStatus;
    //List<Long> professions;

    @JsonCreator
    public StudentDTO(
            @JsonProperty("studentId") String studentId,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("userId") String userId,
            @JsonProperty("username") String username,
            @JsonProperty("email") String email,
            @JsonProperty("phone") String phone,
            @JsonProperty("role") String role,
            @JsonProperty("schoolName") String schoolName,
            @JsonProperty("eductionTitle") String eductionTitle,
            @JsonProperty("educationType") String educationType){
        this.studentId=studentId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.userId=userId;
        this.username=username;
        this.email=email;
        this.phone=phone;
        this.role=role;
        this.schoolName = schoolName;
        this.eductionTitle = eductionTitle;
        this.educationType = educationType;
     }
}
