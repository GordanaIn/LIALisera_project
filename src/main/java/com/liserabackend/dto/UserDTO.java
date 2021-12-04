package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.liserabackend.entity.Role;
import lombok.Value;

import java.util.List;

@Value
public class UserDTO {
    String userId;
    String email;
    List<Role> role;

    @JsonCreator
    public UserDTO(@JsonProperty("userId")String userId,
                       @JsonProperty("email")String email,
                       @JsonProperty("role")  List<Role> role){
            this.userId=userId;
             this.email=email;
            this.role=role;
    }
}
