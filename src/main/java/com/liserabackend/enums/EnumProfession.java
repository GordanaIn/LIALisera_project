package com.liserabackend.enums;

public enum EnumProfession {
    FULLSTACK("Full Stack Developer"),
    FRONTEND("Frontend Developer"),
    BACKEND("Backend Developer"),
    JAVAUTVECKLARE("Java Utvecklare"),
    CSHARP("C# Utvecklare");

    private final String textVal;

    EnumProfession(String textVal){
        this.textVal = textVal;
    }

    public String getTextVal(){
        return this.textVal;
    }
}
