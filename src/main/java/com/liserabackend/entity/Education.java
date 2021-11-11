package com.liserabackend.entity;

import com.liserabackend.enums.EducationType;
import com.liserabackend.enums.SchoolName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity(name="educations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Education {
    @Id
    @Column(columnDefinition = "varchar(100)") private String id;
    private String title;

    @Column(length = 50)
    private String schoolName;

    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public Education(String title, String schoolName, User user){
        this.id= UUID.randomUUID().toString();
        this.title=title;
        this.schoolName=schoolName;
        this.user=user;
    }
}
