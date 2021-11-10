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
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private SchoolName schoolName;

    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EducationType educationType;


    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    public Education(String title, SchoolName schoolName,EducationType educationType, User user){
        this.id= UUID.randomUUID().toString();
        this.title=title;
        this.schoolName=schoolName;
        this.educationType=educationType;
        this.user=user;
    }
}
