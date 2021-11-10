package com.liserabackend.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name = "files")
@NoArgsConstructor
public class FilesUpload {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String resume;
    private String video;
    private String persLetter;



    @JsonCreator
    public void getOriginalFileName(
            @JsonProperty("id") Long id,
            @JsonProperty("resume") String resume,
            @JsonProperty("video") String video,
            @JsonProperty("persLetter") String persLetter){
        this.id = id;
        this.resume = resume;
        this.video = video;
        this.persLetter = persLetter;

    }

}
