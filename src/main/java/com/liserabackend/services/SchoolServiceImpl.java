package com.liserabackend.services;

import com.liserabackend.dto.CreateSchool;
import com.liserabackend.entity.School;
import com.liserabackend.entity.repository.SchoolRepository;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
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

    public Optional<School> getSchoolByUserId(String userId) throws UseException {
        return Optional.of(schoolRepository.findByUserId(userId).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND)));
    }

    public Optional<School> addSchool(CreateSchool createSchool) {
        return null;
    }


}
