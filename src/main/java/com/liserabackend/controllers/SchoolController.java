package com.liserabackend.controllers;

import com.liserabackend.dto.*;
import com.liserabackend.entity.School;
import com.liserabackend.entity.User;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import com.liserabackend.services.SchoolService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders ="*")
@RequestMapping("/api/school")
public class SchoolController {
    private final SchoolService schoolService;
    @GetMapping()
    public List<SchoolDTO> getSchools() {
        return schoolService.getSchools().map(this::toSchoolDTO).collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public SchoolDTO getSchoolByUserId(@PathVariable("userId") String userId) throws UseException {
        return schoolService.getSchoolByUserId(userId).map(this::toSchoolDTO).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<?> saveCompany(@RequestBody CreateSchool createSchool) throws UseException {
        return ResponseEntity.ok(schoolService.addSchool(createSchool).map(this::toSchoolDTO));
    }

    private SchoolDTO toSchoolDTO(School school) {
        User user=school.getUser();
        return new SchoolDTO(
                school.getId(),
                school.getName(),
                school.getPhone(),
                school.getOrgNumber(),
                user.getId(),
                user.getUsername(),
                user.getRole().toString()
        );
    }
}
