package com.liserabackend.services;

import com.liserabackend.entity.School;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class SchoolServiceImpl implements ISchool {
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
