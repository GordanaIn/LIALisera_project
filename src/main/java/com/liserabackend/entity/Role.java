package com.liserabackend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity(name = "roles") @AllArgsConstructor @NoArgsConstructor @Data
public class Role {
    @Id
    @Column(columnDefinition = "varchar(100)")
    private String id;
    private String name;

    public Role(String name) {
        this.id= UUID.randomUUID().toString();
        this.name=name;
    }
}
