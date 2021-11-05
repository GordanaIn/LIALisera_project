package com.liserabackend.services;

import com.liserabackend.entity.Company;
import com.liserabackend.entity.School;
import com.liserabackend.exceptions.UseException;


import java.util.Optional;
import java.util.stream.Stream;

public interface ISchool {
    School saveStudent(School school);
    Stream<School> getSchools();
    Stream<School> getByOrgNumber(String orgNo);
    Optional<School> updateSchool(String schoolId, School school);
}
