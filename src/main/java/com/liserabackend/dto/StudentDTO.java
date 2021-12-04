package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liserabackend.entity.Role;
import lombok.Value;

import java.util.List;

@Value
public class StudentDTO {
    String studentId;
    String firstName;
    String lastName;
    String userId;
    String password;
    String email;
    String phone;
    String linkedIn;
    List<Role> role;
    String schoolName;

    @JsonCreator
    public StudentDTO(
            @JsonProperty("studentId") String studentId,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("userId") String userId,
            @JsonProperty("password") String password,
            @JsonProperty("email") String email,
            @JsonProperty("phone") String phone,
            @JsonProperty("linkedIn") String linkedIn,
            @JsonProperty("role")     List<Role> role,
            @JsonProperty("schoolName") String schoolName){
        this.studentId=studentId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.userId=userId;
        this.password=password;
        this.email=email;
        this.phone=phone;
        this.linkedIn=linkedIn;
        this.role=role;
        this.schoolName=schoolName;
     }
}
