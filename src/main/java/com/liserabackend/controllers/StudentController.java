package com.liserabackend.controllers;

import com.liserabackend.dto.*;
import com.liserabackend.dto.CreateStudent;
import com.liserabackend.entity.Education;
import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.enums.SchoolName;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import com.liserabackend.services.StudentServiceImp;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders ="*")
@RequestMapping("/api/student")
public class StudentController {
    private final StudentServiceImp studentService;

    @GetMapping()
    public List<StudentDTO> getStudents() {
        return studentService.getStudents().map(this::toStudentDTO).collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public StudentDTO getStudentByUserId(@PathVariable("userId") String userId) throws UseException {
        return studentService.getStudentByUserId(userId).map(this::toStudentDTO).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(CreateStudent student){

        return  null;
    }
    @PostMapping("/update/email/{userId}")
    public UserDTO updateEmail(@PathVariable ("userId") String userId, @RequestBody EmailDTO emailDTO) throws UseException {
        return studentService.updateEmail(userId, emailDTO.getEmail()).map(this::toUserDTO).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND));
    }
    @PostMapping("/update/username/{userId}")
    public UserDTO updateUserName(@PathVariable ("userId") String userId, @RequestBody UsernameDTO usernameDTO) throws UseException {
        return studentService.updateUsername(userId, usernameDTO.getUsername()).map(this::toUserDTO).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND));
    }
    @PostMapping("/update/user/{userId}")
    public UserDTO updateUser(@PathVariable ("userId") String userId, @RequestBody UserDTO userDTO) throws UseException {
        return null;//studentService.updateUser(userId, userDTO).map(this::toUserDTO).orElseThrow(()->new UseException(UseExceptionType.User_NOT_FOUND));
    }
    @GetMapping("/eduction/{userId}")
    public List<Education> getStudentEducations(@PathVariable ("userId") String userId) throws UseException {
        return studentService.getStudentEducations(userId).collect(Collectors.toList());
    }
    @PutMapping("/update/{userId}")
    public StudentDTO updateStudentProfile(@PathVariable("userId") String userId, @RequestBody CreateStudent student) throws UseException {
        return studentService.updateProfile(userId, student).map(this::toStudentDTO).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND));
    }
    @SneakyThrows
    private StudentDTO toStudentDTO(Student student) {
        User user=student.getUser();
        Optional<Education> education = studentService.getEducations(user.getId()).findFirst();

        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                student.getPhone(),
                user.getRole().toString(),
                (education.isPresent())? education.get().getSchoolName().toString() : " ",
                (education.isPresent())? education.get().getTitle() : " ",
                (education.isPresent())? education.get().getEducationType().toString() : " "
         );

    }
    private UserDTO toUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().toString()
        );
    }

}
