package com.liserabackend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import java.util.UUID;

@Data
@Entity(name = "files")
@NoArgsConstructor
public class StudentInformation {
    @Id
    @Column(columnDefinition = "varchar(100)") private String Id;
    private String personalLetter;
    @Lob
    private String resume;
    private String linkedInUrl;
    @Lob
    private String video;

    public StudentInformation(String personalLetter, String resume, String linkedInUrl, String video){
        this.Id= UUID.randomUUID().toString();
        this.personalLetter = personalLetter;
        this.resume = resume;
        this.video = video;
        this.linkedInUrl = linkedInUrl;
    }
}
