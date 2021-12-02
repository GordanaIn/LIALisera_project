package com.liserabackend.dto;

import lombok.Value;

@Value
public class ModifyPasswordDTO {
    String email;
    String currentPassword;
    String newPassword;

}
