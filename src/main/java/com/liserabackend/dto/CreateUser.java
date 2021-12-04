package com.liserabackend.dto;

import com.liserabackend.entity.Role;
import com.liserabackend.enums.EnumRole;
import lombok.Value;

import java.util.List;

@Value

public class CreateUser {
    String email;
    String Password;
    List<Role> role;
}
