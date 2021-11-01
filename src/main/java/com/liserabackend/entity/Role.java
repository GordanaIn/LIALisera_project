package com.liserabackend.entity;
/*
import com.liserabackend.enums.EnumRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;
*/

/** Do We really need a ROLE TABLE */
/*
@Data
@Entity(name="roles")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Role {
    @Id@Column(columnDefinition = "varchar(100)") private String Id;

    @Enumerated(EnumType.STRING)
    @Column(length = 25)
    private EnumRole name;
    /** A particular role is can be associated with many user, s001,s002,s003 had role Student */
    /** Do I need to know users associated with Admin role:- if so set<User> users*/

  /*
    public Role(EnumRole name){
        this.Id= UUID.randomUUID().toString();
        this.name = name;
    }
}
*/