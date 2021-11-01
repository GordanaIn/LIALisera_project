package com.liserabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;


@Data
@Entity(name="schools")
@NoArgsConstructor()
@AllArgsConstructor
public class School {
    @Id
    @Column(columnDefinition = "varchar(100)") private String Id;

    @OneToMany()
    @JoinColumn(name="user_id", nullable = false)
    private Set<User> users=new HashSet<>();/** Just to be able to login to the school admin */
    @Column(length = 50)
    private String name;
    private String phone;
    private String orgNumber;

    public School(String name,String phone, String orgNumber,Set<User> users ){
        assert users!=null; /** A School without user not allowed */
        this.Id= UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.orgNumber=orgNumber;
        this.users.addAll(users);
    }
}
