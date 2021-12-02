package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class CompanyDTO {
    String id;
    String name;
    String orgNumber;
    String companyEmail;
    String userId;
    String role;

    @JsonCreator
    public CompanyDTO(
            @JsonProperty("id")String id,
            @JsonProperty("name")String name,
            @JsonProperty("orgNumber")String orgNumber,
            @JsonProperty("companyEmail")String companyEmail,
            @JsonProperty("userId")String userId,
            @JsonProperty("role")String role){
        this.id=id;
        this.name=name;
        this.orgNumber=orgNumber;
        this.companyEmail=companyEmail;
        this.userId=userId;
        this.role=role;
    }
}
