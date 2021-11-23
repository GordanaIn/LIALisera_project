package com.liserabackend.services;

import com.liserabackend.dto.CreateSchool;
import com.liserabackend.entity.School;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.SchoolRepository;
import com.liserabackend.entity.repository.UserRepository;
import com.liserabackend.enums.EnumRole;
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
    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
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

    public Optional<School> addSchool(CreateSchool createSchool) throws UseException {
        //check if user found on user, then on company
        if(userRepository.findByUsername(createSchool.getUsername()).isPresent())
            throw new UseException(UseExceptionType.USER_ALREADY_EXIST);
        User user=new User(createSchool.getUsername(), createSchool.getUserEmail(),createSchool.getPassword(), EnumRole.ROLE_SCHOOL);
        user=userRepository.save(user);
        School school=new School(createSchool.getName(),createSchool.getPhone(),createSchool.getOrganizationNumber(), createSchool.getSchoolEmail(), user);
        school=schoolRepository.save(school);
        return  Optional.of(school);
    }


}
