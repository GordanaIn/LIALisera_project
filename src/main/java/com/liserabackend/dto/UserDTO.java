package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class UserDTO {
    String userId;
    String email;
    String role;

    @JsonCreator
    public UserDTO(@JsonProperty("userId")String userId,
                       @JsonProperty("email")String email,
                       @JsonProperty("role")String role){
            this.userId=userId;
             this.email=email;
            this.role=role;
    }
}
