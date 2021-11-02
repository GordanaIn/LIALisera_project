package com.liserabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;


@Data
@Entity(name="schools")
@NoArgsConstructor()
@AllArgsConstructor
public class School {
    @Id
    @Column(columnDefinition = "varchar(100)") private String id;

    @OneToMany()
    @JoinColumn(name="user_id")
    private Set<User> users=new HashSet<>();/** Just to be able to login to the school admin */


    @Column(length = 50)
    private String name;
    private String phone;
    private String orgNumber;

    public School(String name,String phone, String orgNumber, User user){
        assert users!=null; /** A School without user not allowed */
        this.id= UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.orgNumber=orgNumber;
        this.users.add(user);
    }
}
