package com.liserabackend.services;

import com.liserabackend.entity.School;
import com.liserabackend.entity.repository.SchoolRepository;
import com.liserabackend.services.interfaces.ISchool;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class SchoolServiceImpl implements ISchool {
    SchoolRepository schoolRepository;
    @Override
    public School saveStudent(School school) {
        return schoolRepository.save(school);
    }

    @Override
    public Stream<School> getSchools() {
        return schoolRepository.findAll().stream();
    }

    @Override
    public Stream<School> getByOrgNumber(String orgNo) {
        return schoolRepository.findAll().stream().filter(s->s.getOrgNumber().equals(orgNo));
    }

    @Override
    public Optional<School> updateSchool(String schoolId, School school) {
        return Optional.empty();
    }
}
