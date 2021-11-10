package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;


@Value
public class SchoolDTO {
    String id;
    String name;
    String phone;
    String orgNumber;
    String userId;
    String username;
    String email;
    String role;

    public SchoolDTO(
            @JsonProperty("id")String id,
            @JsonProperty("name")String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("orgNumber")String orgNumber,
            @JsonProperty("userId")String userId,
            @JsonProperty("username")String username,
            @JsonProperty("email")String email,
            @JsonProperty("role")String role){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.orgNumber=orgNumber;
        this.userId=userId;
        this.username=username;
        this.email=email;
        this.role=role;
    }
}
