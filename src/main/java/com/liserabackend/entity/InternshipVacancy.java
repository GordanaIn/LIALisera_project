package com.liserabackend.entity;

/**
 * Advert
 *     An advert belongs to a company but a company can have many advert.
 */
import com.liserabackend.enums.AdvertStatus;
import com.liserabackend.enums.EnumProfession;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Data
@Entity(name="adverts")
@AllArgsConstructor
@NoArgsConstructor
public class InternshipVacancy {
    @Id
    @Column(columnDefinition = "varchar(100)") private String Id;
    private String title;
    private String description;
    private String duration; //how long the internship lasts
    private AdvertStatus status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePosted;
    private String contactEmployer;
    private String contactPhone;


    /** required profession for the advert */
    @Column(length = 100)
    private EnumProfession requiredProfession;
    /** A single advert associated with one company only but a company can post several advert */

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Set<Company> companies=new HashSet<>();

    /** List of students applied for an advert- Many student can be applied to a single advert */
    @ManyToMany()
    @JoinColumn(name="student_id")
    private Set<Student> students=new HashSet<>();

    private boolean approved;/** why? */

    public InternshipVacancy(String title, String description, String duration, AdvertStatus status, LocalDate datePosted, String contactPerson,
                             String contactPhone ){
        this.Id= UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.status=status;
        this.datePosted = datePosted;
        this.contactEmployer=contactPerson;
        this.contactPhone=contactPhone;
    }
    public List<String> getStudentsListAppliedForAdvert(){
        return students.stream()
                .map(student -> student.getPhone())
                .collect(Collectors.toList());
    }
}
