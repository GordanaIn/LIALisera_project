package com.liserabackend.services;

import com.liserabackend.entity.School;
import com.liserabackend.exceptions.UseException;

import java.util.Optional;
import java.util.stream.Stream;

public class ISchoolImpl implements ISchool {
    @Override
    public School saveStudent(School school) {
        return null;
    }

    @Override
    public Stream<School> getSchools() {
        return null;
    }

    @Override
    public Stream<School> getByOrgNumber() {
        return null;
    }

    @Override
    public Optional<School> updateSchool(String schoolId, School school) {
        return Optional.empty();
    }
}
