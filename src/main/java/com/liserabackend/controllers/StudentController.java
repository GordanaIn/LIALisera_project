package com.liserabackend.controllers;

import com.liserabackend.dto.*;
import com.liserabackend.dto.CreateStudent;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import com.liserabackend.services.StudentServiceImp;
import com.liserabackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/student")
public class StudentController {
    private final StudentServiceImp studentService;
    private final UserService userService;

    @GetMapping()
    public List<StudentDTO> getStudents() {
        return studentService.getStudents()
                .map(this::toStudentDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public StudentDTO getStudentByUserId(@PathVariable("userId") String userId) throws UseException {
        return studentService.getStudentByUserId(userId)
                .map(this::toStudentDTO)
                .orElseThrow(() -> new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<?> saveStudent(@RequestBody CreateStudent createStudent) throws UseException {
        return ResponseEntity.ok(userService.addStudent(createStudent)
                .map(this::toStudentDTO));
    }

    @PatchMapping("/email/{userId}")
    public UserDTO updateEmail(@PathVariable("userId") String userId,
                               @RequestBody EmailDTO emailDTO) throws UseException {
        return userService.updateEmail(userId, emailDTO.getEmail())
                .map(this::toUserDTO)
                .orElseThrow(() -> new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    @PatchMapping("/username/{userId}")
    public UserDTO updateUserName(@PathVariable("userId") String userId,
                                  @RequestBody UsernameDTO usernameDTO) throws UseException {
        return userService.updateUsername(userId, usernameDTO.getUsername())
                .map(this::toUserDTO)
                .orElseThrow(() -> new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    @PatchMapping("/password/{userId}")
    public UserDTO updatePassword(@PathVariable("userId") String userId,
                                  @RequestBody PasswordDTO passwordDTO) throws UseException {
        return userService.updatePassword(userId, passwordDTO.getPassword())
                .map(this::toUserDTO)
                .orElseThrow(() -> new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    @PatchMapping("/modifyPassword")
    public UserDTO modifyPassword(@RequestBody ModifyPasswordDTO modifyPasswordDTO) throws UseException {
        return userService.modifyPassword(modifyPasswordDTO)
                .map(this::toUserDTO)
                .orElseThrow(() -> new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    @PatchMapping("update/{userId}")
    public StudentDTO updateStudentProfile(@PathVariable("userId") String userId,
                                           @RequestBody CreateStudent student) throws UseException {
        return studentService.updateProfile(userId, student)
                .map(this::toStudentDTO)
                .orElseThrow(() -> new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    //check again
    @PatchMapping("/{userId}/{internshipId}")
    public boolean applyInternship(@PathVariable("userId") String userId,
                                   @PathVariable("internshipId") String internshipId) throws UseException {
        return studentService.applyInternship(userId, internshipId);
    }

    private StudentDTO toStudentDTO(Student student) {
        User user = student.getUser();

        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                student.getPhone(),
                (student.getLinkedInUrl() != "") ? student.getLinkedInUrl() : "",
                user.getRole().toString(),
                student.getSchoolName()
        );
    }

    private UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().toString()
        );
    }

}
