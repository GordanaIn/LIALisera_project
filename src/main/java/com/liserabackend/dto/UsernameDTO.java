package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class UsernameDTO {
    String username;

    public UsernameDTO(@JsonProperty("username")String username){
        this.username = username;
    }
}
