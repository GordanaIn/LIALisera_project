package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class UsernameDTO {
    String username;
    /** Cannot construct instance and cannot deserialize from Object value problem is fixed using @JsonCreator */
    public UsernameDTO(@JsonProperty("username") String username){
        super();
        this.username = username;
    }
}
