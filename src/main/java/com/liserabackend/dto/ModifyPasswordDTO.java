package com.liserabackend.dto;

import lombok.Value;

@Value
public class ModifyPasswordDTO {
    String username;
    String currentPassword;
    String newPassword;

}
