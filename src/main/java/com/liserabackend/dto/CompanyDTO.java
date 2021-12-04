package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.liserabackend.entity.Employee;
import lombok.Value;

import java.util.List;

@Value
public class CompanyDTO {
    String id;
    String name;
    String orgNumber;
    List<Employee> employees;

    @JsonCreator
    public CompanyDTO(
            @JsonProperty("id")String id,
            @JsonProperty("name")String name,
            @JsonProperty("orgNumber")String orgNumber,
            @JsonProperty("employees")List<Employee> employees){
        this.id=id;
        this.name=name;
        this.orgNumber=orgNumber;
        this.employees=employees;
    }
}
