package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

import javax.validation.constraints.Email;

@Value
public class EmailDTO {
    @Email String email;

    public EmailDTO(@JsonProperty("email") String email){
        this.email = email;
    }
}
