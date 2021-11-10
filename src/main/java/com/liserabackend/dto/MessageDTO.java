package com.liserabackend.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
public class MessageDTO {
    String message;

    @JsonCreator
    public MessageDTO(
            @JsonProperty("message")String message){
        this.message = message;
    }

}
