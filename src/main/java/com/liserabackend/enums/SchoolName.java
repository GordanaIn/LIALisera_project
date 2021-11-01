package com.liserabackend.enums;
/* I need to make it */
public enum SchoolName {
    SCHOOL_ECUTBILDNING("EC Utbildning AB"),
    SCHOOL_NACKADEMIN("Nackademin AB"),
    SCHOOL_KTH("KTH University"),
    SCHOOL_LNU("Linnaeus University");

    private final String textVal;

    SchoolName(String textVal){
        this.textVal = textVal;
    }

    public String getTextVal(){
        return this.textVal;
    }
}
