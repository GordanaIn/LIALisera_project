package com.liserabackend.enums;

import java.util.stream.Stream;

/**
 *  1. ROLE_ADMIN
 *     Main admin- (change school,employee,student, change document,change credential)
 *     This is done in the back-end.
 *     createStudent, update person, add
 *
 *  2.  ROLE_SCHOOL
 *      Admin student and employer
 */
public enum EnumRole {
    ROLE_ADMIN,
    ROLE_SCHOOL,
    ROLE_STUDENT,
    ROLE_EMPLOYER;

    public static Stream<EnumRole> getRoles() {
        return Stream.of(EnumRole.values());
    }

}
