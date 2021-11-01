package com.liserabackend.entity;

/**
 * Company
 *      private User user -> one to one or Many to one??
 *      Boolean status to prove later by Admin - handle if a company is potential candidate for internship.
 *      A company has many favourites:- Set of favorite internship List<Internship> favourites
 *      A Student has many document like personalLetter, resume, video (List<Document> documents )
 */
import com.liserabackend.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name="companies")
@AllArgsConstructor
@NoArgsConstructor
/** If the company handle only advert no need to have a class */
public class Company {
    @Id
    @Column(columnDefinition = "varchar(100)") private String Id;

    @OneToMany()
    @JoinColumn(name="user_id", nullable = false)
    private Set<User> users=new HashSet<>();/** Just to be able to login to the school admin */

    private String name;
    private String orgNumber;
    private EnumStatus status; /** To handle if a company is potential and valid candidate for Advert Internship */

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="student_id")
    private Set<Student> favourites=new HashSet<>();

    /** A company can post many Advert */
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="advert_id")
    private Set<InternshipVacancy> internshipVacancyList =new HashSet<>();

    public Company(String name,String orgNumber,User user ){
        assert user!=null; /** A student without user not allowed */
        this.Id= UUID.randomUUID().toString();
        this.name = name;
        this.orgNumber = orgNumber;
        this.status= EnumStatus.NOT_APPROVED;
        this.users.add(user);
    }

}
