package com.liserabackend.dto;

import lombok.Value;

import java.util.List;
@Value
public class UserDTO {
    String userId;
    String username;
    String email;
    //List<String> roles;
    String role;
}
