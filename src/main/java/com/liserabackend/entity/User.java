package com.liserabackend.entity;

import com.liserabackend.enums.EnumRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity(name="users")
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id @Column(columnDefinition = "varchar(100)")
    private String id;

    private String username;
    private String password;

    @ManyToMany(fetch=EAGER)
    Collection<Role> roles=new ArrayList<>();

    public User(String username, String password){
        this.id= UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }
}
