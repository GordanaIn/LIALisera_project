package com.liserabackend.controllers;

import com.liserabackend.dto.SchoolDTO;
import com.liserabackend.dto.StudentDTO;
import com.liserabackend.entity.School;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.services.SchoolServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders ="*")
@RequestMapping("/api/school")
public class SchoolController {
    private final SchoolServiceImpl schoolService;
    @GetMapping()
    public List<SchoolDTO> getSchools() {
        return schoolService.getSchools().map(this::toSchoolDTO).collect(Collectors.toList());
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
                user.getEmail(),
                user.getRole().toString()
        );
    }
}
