package com.liserabackend.entity;

import com.liserabackend.enums.EnumRole;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@Entity(name="users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id @Column(columnDefinition = "varchar(100)") private String id;
    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @Size(max = 120)
    private String password; // byte[] encryptedPassword;

    /**  A user has set of Role but in these scenarios A user has only one role */
    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private EnumRole role;

    public User(String username, String email, String password, EnumRole role){
        this.id= UUID.randomUUID().toString();
        this.username = username;
        this.email = email;
        this.password = password;
        this.role=role;
    }
}
