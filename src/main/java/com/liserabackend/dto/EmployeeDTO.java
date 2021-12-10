package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.liserabackend.entity.Role;
import lombok.Value;

import java.util.List;

@Value
public class EmployeeDTO {
    String studentId;
    String firstName;
    String lastName;
    String company;
    String password;
    String email;
    List<Role> role;

    @JsonCreator
    public EmployeeDTO(
            @JsonProperty("studentId") String studentId,
            @JsonProperty("firstName") String firstName,
            @JsonProperty("lastName") String lastName,
            @JsonProperty("password") String password,
            @JsonProperty("email") String email,
            @JsonProperty("company") String company,
            @JsonProperty("role")     List<Role> role) {

        this.studentId=studentId;
        this.firstName=firstName;
        this.lastName=lastName;
        this.password=password;
        this.email=email;
        this.company=company;
        this.role=role;

    }
}
