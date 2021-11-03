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

    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    @Column(length = 50)
    private String name;
    private String phone;
    private String orgNumber;

    public School(String name,String phone, String orgNumber, User user){
        assert user!=null; /** A School without user not allowed */
        this.id= UUID.randomUUID().toString();
        this.name = name;
        this.phone = phone;
        this.orgNumber=orgNumber;
        this.user=user;
    }
}
