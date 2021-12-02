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

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    private String name;
    private String orgNumber;
    private String email;
    /** Company email */
    private EnumStatus status; /** To handle if a company is potential and valid candidate for Advert Internship */

    /** A company can post many Advert */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)//, mappedBy = "internshipVacancy"
    @JoinColumn(name = "internshipAdvert_id")
    private Set<InternshipAdvert> internshipVacancyList = new HashSet<>();

    public Company(String name, String orgNumber, String email, User user) {
        assert user != null; /** A company without user not allowed */
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.orgNumber = orgNumber;
        this.email = email;
        this.user = user;
        this.status = EnumStatus.NOT_APPROVED;
    }

}
