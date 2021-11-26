package com.liserabackend.entity;

import com.liserabackend.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.*;

@Data
@Entity(name="students")
@NoArgsConstructor()
@AllArgsConstructor
public class Student {
    @Id
    @Column(columnDefinition = "varchar(100)") private String id;

    /** ManyToOne or OneToOne ?? */
    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user; //A user should have a student not a student having a user.

    @NotBlank
    @Size(max = 30)
    private String firstName;

    @NotBlank
    @Size(max = 30)
    private String lastName;
    private String phone;
    private String linkedInUrl;

    @OneToOne()
    @JoinColumn(name="files_id")
    FilesUpload document;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Education> educations=new HashSet<>();
    private EnumStatus status=EnumStatus.NOT_APPROVED; /** to handle if a student is potential candidate for internship */

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name="advert_id")
    private Set<InternshipVacancy> favourites=new HashSet<>();

    public Student(String firstName,String lastName,String phone, User user ){
        assert user!=null; /** A student without user not allowed */
        this.id= UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.user=user;
   }
    //find advert
}
