package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

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
    //String schoolName;
   /* String resume;
    String personalLetter;
    String linkedInUrl;*/
    //String systemStatus;
    //List<Long> professions;

    public StudentDTO(
                   @JsonProperty("studentId")String studentId,
                   @JsonProperty("firstName")String firstName,
                   @JsonProperty("lastName")String lastName,
                   @JsonProperty("userId")String userId,
                   @JsonProperty("username")String username,
                   @JsonProperty("email")String email,
                   @JsonProperty("phone")String phone,
                   @JsonProperty("role")String role){
        this.studentId=studentId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.userId=userId;
        this.username=username;
        this.email=email;
        this.phone=phone;
        this.role=role;
    }
}
