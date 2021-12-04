package com.liserabackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/** Who is the candidate for what company: Offer  */
@Data
@Entity(name="matches")
@NoArgsConstructor
public class Match {
    @Id
    @Column(columnDefinition = "varchar(100)") private String id;
    @OneToOne()
    @JoinColumn(name="company_id")
    private Company company;
   /* @ManyToMany()
    @JoinColumn(name="student_id")
    private Set<Student> students=new HashSet<>();*//** What if a company have many candidate student offer */
    @OneToOne
    @JoinColumn(name="student_id")
    private Student student;
    @OneToOne()
    @JoinColumn(name="advert_id")//, nullable = false
    private InternshipAdvert internshipAdvert;

    public Match(String name,Company company,Student student,InternshipAdvert internshipAdvert  ){
        assert company!=null; /** A match without company, student, and internshipVacancy is not allowed */
        assert  student!=null;
        assert  internshipAdvert!=null;
        this.id= UUID.randomUUID().toString();
        this.company = company;
        this.student=student;
        this.internshipAdvert= internshipAdvert;
    }
}
