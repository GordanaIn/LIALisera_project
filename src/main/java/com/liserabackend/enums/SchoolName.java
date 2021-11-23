package com.liserabackend.enums;
/* I need to make it */
public enum SchoolName {
    ECUTBILDNING("EC Utbildning AB"),
    NACKADEMIN("Nackademin AB"),
    KTH("KTH University"),
    LNU("Linnaeus University"),
    JENSEN("Jensen Academy");
    private final String textVal;

    SchoolName(String textVal){
        this.textVal = textVal;
    }

    public String getTextVal(){
        return this.textVal;
    }
}
