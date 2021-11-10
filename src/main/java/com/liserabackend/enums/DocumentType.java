package com.liserabackend.enums;


import java.util.stream.Stream;

public enum DocumentType {
    CV,
    PERSONAL_LETTER,
    POLICY_FOR_STUDENT;

    public static Stream<DocumentType> getDocuments() {
        return Stream.of(DocumentType.values());
    }
}
