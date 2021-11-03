package com.liserabackend.controllers;

import com.liserabackend.dto.*;
import com.liserabackend.dto.CreateStudent;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import com.liserabackend.services.StudentServiceImp;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        return studentService.getStudentByUserId(userId).map(this::toStudentDTO).orElseThrow(()->new UseException(UseExceptionType.User_NOT_FOUND));
    }
    @PostMapping("/save")
    public ResponseEntity<Student> saveStudent(CreateStudent student){

        return  null;
    }
    @GetMapping("/{userId}/update/email")
    public UserDTO updateEmail(@PathVariable ("userId") String userId, @RequestBody EmailDTO emailDTO) throws UseException {
        return studentService.updateEmail(userId, emailDTO.getEmail()).map(this::toUserDTO).orElseThrow(()->new UseException(UseExceptionType.User_NOT_FOUND));
    }
    @GetMapping("/{userId}/update/username")
    public UserDTO updateUserName(@PathVariable ("userId") String userId, @RequestBody UsernameDTO usernameDTO) throws UseException {
        return studentService.updateUsername(userId, usernameDTO.getUsername()).map(this::toUserDTO).orElseThrow(()->new UseException(UseExceptionType.User_NOT_FOUND));
    }
    @PutMapping("/update/{userId}")
    public StudentDTO updateStudentProfile(@PathVariable("userId") String userId, @RequestBody CreateStudent student) throws UseException {
        return studentService.updateProfile(userId, student).map(this::toStudentDTO).orElseThrow(()->new UseException(UseExceptionType.User_NOT_FOUND));
    }
    @SneakyThrows
    private StudentDTO toStudentDTO(Student student) {
        User user=student.getUser();

        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                student.getPhone(),
                user.getRole().toString()
              );
    }
   private UserDTO toUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().toString()
                // or user.getRoles().stream().map(role->role.getName().toString()).collect(Collectors.toList())
        );
    }

}
