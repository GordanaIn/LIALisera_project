package com.liserabackend.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Entity(name = "files")
@NoArgsConstructor
public class FilesUpload {
    @Id
    @Column(columnDefinition = "varchar(100)") private String id;
    @Lob
    private String resume;
    @Lob
    private String video;
    @Lob
    private String persLetter;

    @JsonCreator
    public void getOriginalFileName(
            @JsonProperty("resume") String resume,
            @JsonProperty("video") String video,
            @JsonProperty("persLetter") String persLetter){

        this.id= UUID.randomUUID().toString();
        this.resume = resume;
        this.video = video;
        this.persLetter = persLetter;
    }
}
