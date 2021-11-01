package com.liserabackend.enums;

public enum EnumProfession {
    PROFESSION_FULLSTACK("Full Stack Developer"),
    PROFESSION_FRONTEND("Frontend Developer"),
    PROFESSION_BACKEND("Backend Developer"),
    PROFESSION_JAVAUTVECKLARE("Java Utvecklare"),
    PROFESSION_CSHARP("C# Utvecklare");

    private final String textVal;

    EnumProfession(String textVal){
        this.textVal = textVal;
    }

    public String getTextVal(){
        return this.textVal;
    }
}
