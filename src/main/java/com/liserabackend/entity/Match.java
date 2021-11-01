package com.liserabackend.entity;

import com.liserabackend.enums.EnumStatus;
import lombok.AllArgsConstructor;
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
    @Column(columnDefinition = "varchar(100)") private String Id;
    @OneToOne()
    @JoinColumn(name="company_id", nullable = false)
    private Company company;
    @ManyToMany()
    @JoinColumn(name="student_id", nullable = false)
    private Set<Student> students=new HashSet<>();/** What if a company have many candidate student offer */
    @OneToOne()
    @JoinColumn(name="advert_id", nullable = false)
    private InternshipVacancy internshipVacancy;

    public Match(String name,Company company,Set<Student> students,InternshipVacancy internshipVacancy  ){
        assert company!=null; /** A match without company, student, and internshipVacancy is not allowed */
        assert  students!=null;
        assert  internshipVacancy!=null;
        this.Id= UUID.randomUUID().toString();
        this.company = company;
        this.students.addAll(students);
        this.internshipVacancy= internshipVacancy;
    }
}
