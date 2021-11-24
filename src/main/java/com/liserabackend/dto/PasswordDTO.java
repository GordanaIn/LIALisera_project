package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class PasswordDTO {
    String password;
    @JsonCreator
    public PasswordDTO(@JsonProperty("password") String password){
        super();
        this.password = password;
    }
}
