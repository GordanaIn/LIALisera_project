package com.liserabackend.entity;

import com.liserabackend.enums.EnumStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity(name="employees")
@NoArgsConstructor()
@AllArgsConstructor
public class Employee {
    @Id
    @Column(columnDefinition = "varchar(100)") private String id;

    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;
    @NotBlank
    @Size(max = 30)
    private String firstName;

    @NotBlank
    @Size(max = 30)
    private String lastName;
    private String email;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "company_id")
    private Company company;

    public Employee(String firstName,String lastName,String email, User user ){
        assert user!=null; /** An Employee without user not allowed */
        this.id = UUID.randomUUID().toString();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.user = user;
    }
}
