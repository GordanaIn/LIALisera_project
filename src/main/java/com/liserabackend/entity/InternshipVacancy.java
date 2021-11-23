package com.liserabackend.entity;

import com.liserabackend.enums.EnumProfession;
import com.liserabackend.enums.InternshipVacancyStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity(name="internshipVacancies")
@AllArgsConstructor
@NoArgsConstructor
public class InternshipVacancy {
    @Id
    @Column(columnDefinition = "varchar(100)") private String id;
    private String title;
    private String description;
    private String duration; /** how long the internship lasts */

    private InternshipVacancyStatus status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePosted;
    private String contactEmployer;
    private String contactPhone;


    /** required profession for the advert */
    @Column(length = 100)
    private EnumProfession requiredProfession;
    /** A single advert associated with one company only but a company can post several advert */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

    /** List of students applied for an advert- Many student can be applied to a single advert */
    @ManyToMany()
    @JoinColumn(name="student_id")
    private Set<Student> students=new HashSet<>();

    private boolean approved;/** why? */

    public InternshipVacancy(String title, String description, String duration,  LocalDate datePosted, String contactPerson,
                             String contactPhone, Company company ){
        this.id= UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.status=InternshipVacancyStatus.OPEN;
        this.datePosted = datePosted;
        this.contactEmployer=contactPerson;
        this.contactPhone=contactPhone;
        this.company=company;
    }
    public List<String> getStudentsListAppliedForAdvert(){
        return students.stream()
                .map(Student::getPhone)
                .collect(Collectors.toList());
    }

}
