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

    @PostMapping("")
    public ResponseEntity<?> saveStudent(@RequestBody CreateStudent createStudent) throws UseException {
      return ResponseEntity.ok(studentService.addStudent(createStudent).map(this::toStudentDTO));
    }

    @PatchMapping("/email/{userId}")
    public UserDTO updateEmail(@PathVariable ("userId") String userId, @RequestBody EmailDTO emailDTO) throws UseException {
        return studentService.updateEmail(userId, emailDTO.getEmail()).map(this::toUserDTO).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    @PatchMapping("/username/{userId}")
    public UserDTO updateUserName(@PathVariable ("userId") String userId, @RequestBody UsernameDTO usernameDTO) throws UseException {
        return studentService.updateUsername(userId, usernameDTO.getUsername()).map(this::toUserDTO).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    @PatchMapping("/password/{userId}")
    public UserDTO updatePassword(@PathVariable ("userId") String userId, @RequestBody PasswordDTO passwordDTO) throws UseException {
        return studentService.updatePassword(userId, passwordDTO.getPassword()).map(this::toUserDTO).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND));
    }
    @PatchMapping("/modifyPassword")
    public UserDTO modifyPassword(@RequestBody ModifyPasswordDTO modifyPasswordDTO) throws UseException {
        return studentService.modifyPassword(modifyPasswordDTO).map(this::toUserDTO).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    @GetMapping("/eduction/{userId}")
    public List<Education> getStudentEducations(@PathVariable ("userId") String userId) throws UseException {
        return studentService.getStudentEducations(userId).collect(Collectors.toList());
    }

    @PatchMapping("update/{userId}")
    public StudentDTO updateStudentProfile(@PathVariable("userId") String userId, @RequestBody CreateStudent student) throws UseException {
        return studentService.updateProfile(userId, student).map(this::toStudentDTO).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    //check again
    @PatchMapping("/{userId}/{internshipId}")
    public boolean applyInternship(@PathVariable ("userId") String userId, @PathVariable ("internshipId") String internshipId) throws UseException {
        return  studentService.applyInternship(userId, internshipId);
    }

    //@SneakyThrows
    private StudentDTO toStudentDTO(Student student) {
        User user=student.getUser();
        Optional<Education> education = studentService.getEducations(user.getId()).findFirst();

        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                student.getPhone(),
                (student.getLinkedInUrl()!="")? student.getLinkedInUrl():"",
                user.getRole().toString(),
                (education.isPresent())? education.get().getSchoolName() : " ",
                (education.isPresent())? education.get().getTitle() : " "
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
