package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.liserabackend.entity.Role;
import lombok.Value;

import java.util.List;


@Value
public class SchoolDTO {
    String id;
    String name;
    String phone;
    String orgNumber;
    String userId;
    String email;
    List<Role> role;

    @JsonCreator
    public SchoolDTO(
            @JsonProperty("id")String id,
            @JsonProperty("name")String name,
            @JsonProperty("phone") String phone,
            @JsonProperty("orgNumber")String orgNumber,
            @JsonProperty("userId")String userId,
            @JsonProperty("email")String email,
            @JsonProperty("role")List<Role> role){
        this.id=id;
        this.name=name;
        this.phone=phone;
        this.orgNumber=orgNumber;
        this.userId=userId;
        this.email=email;
        this.role=role;
    }
}
