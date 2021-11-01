package com.liserabackend.enums;


import java.util.stream.Stream;

public enum DocumentType {
    Document_CV,
    Document_PERSONAL_LETTER,
    Document_POLICY_FOR_STUDENT;


    public static Stream<DocumentType> getDocuments() {
        return Stream.of(DocumentType.values());
    }
}
