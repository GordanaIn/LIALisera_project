package com.liserabackend.entity;

/**
 * Company
 * private User user -> one to one or Many to one??
 * Boolean status to prove later by Admin - handle if a company is potential candidate for internship.
 * A company has many favourites:- Set of favorite internship List<Internship> favourites
 * A Student has many document like personalLetter, resume, video (List<Document> documents )
 */

import com.liserabackend.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name = "companies")
@AllArgsConstructor
@NoArgsConstructor
/** If the company handle only advert no need to have a class */
public class Company {
    @Id
    @Column(columnDefinition = "varchar(100)")
    private String id;

   @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
   @JoinColumn(name = "employee_id")
   private Set<Employee> employees = new HashSet<>();

   private String name;
   private String orgNumber;

    /** Company email */
    private EnumStatus status; /** To handle if a company is potential and valid candidate for Advert Internship */

    /** A company can post many Advert */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)//, mappedBy = "internshipVacancy"
    @JoinColumn(name = "internshipAdvert_id")
    private Set<InternshipAdvert> internshipAdvertList = new HashSet<>();

    public Company(String name, String orgNumber) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.orgNumber = orgNumber;
        this.status = EnumStatus.NOT_APPROVED;
    }

}
