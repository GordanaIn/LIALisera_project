package com.liserabackend.entity;

import com.liserabackend.enums.EducationType;
import com.liserabackend.enums.SchoolName;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
@Entity(name="educations")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Education {
    @Id
    @Column(columnDefinition = "varchar(100)") private String Id;
    private String title;
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private SchoolName schoolName;
    @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private EducationType educationType;

  /*  @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    List<Student> students=new ArrayList<>();*/

    public Education(String name, SchoolName schoolName,EducationType educationType){
        this.Id= UUID.randomUUID().toString();
        this.title=name;
        this.schoolName=schoolName;
        this.educationType=educationType;
    }
}
