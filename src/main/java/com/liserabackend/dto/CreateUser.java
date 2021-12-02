package com.liserabackend.dto;

import com.liserabackend.enums.EnumRole;
import lombok.Value;

@Value

public class CreateUser {
    String email;
    String Password;
    EnumRole role;
}
