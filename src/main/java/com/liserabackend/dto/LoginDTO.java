package com.liserabackend.dto;

import lombok.Value;

import javax.validation.constraints.NotBlank;

@Value
public class LoginDTO {

    @NotBlank String username;
    @NotBlank String password;

}