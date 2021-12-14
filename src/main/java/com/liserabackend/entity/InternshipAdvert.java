package com.liserabackend.entity;

import com.liserabackend.enums.EnumStatus;
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
@Entity(name="internshipAdverts")
@AllArgsConstructor
@NoArgsConstructor
public class InternshipAdvert {
    @Id
    @Column(columnDefinition = "varchar(100)") private String id;

    private String title;
    private String description;
    private String duration; /** how long the internship lasts */
    private InternshipVacancyStatus status;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate datePosted;
    private String contactEmployer;
    private int numberAvailablePositions;

    /** A single advert associated with one company only but a company can post several advert */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id")
    private Company company;

    private EnumStatus isApproved;/** To be proved by the School Admin */

    public InternshipAdvert(String title, String description, String duration, LocalDate datePosted, String contactPerson,
                            int numberAvailablePositions, Company company ){
        this.id= UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.duration = duration;
        this.status=InternshipVacancyStatus.OPEN;
        this.datePosted = datePosted;
        this.contactEmployer=contactPerson;
        this.numberAvailablePositions = numberAvailablePositions;
        this.company=company;
        isApproved=EnumStatus.NOT_APPROVED;
    }
}
