package com.liserabackend.controllers;

import com.liserabackend.dto.*;
import com.liserabackend.dto.CreateStudent;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import com.liserabackend.services.StudentService;
import com.liserabackend.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;
    private final UserService userService;

    @PreAuthorize("hasRole('ROLE_STUDENT')")
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
        return null;
       /* return ResponseEntity.ok(userService.createUser(createStudent)
                .map(this::toStudentDTO));*/
    }

    @PatchMapping("/email/{userId}")
    public UserDTO updateEmail(@PathVariable("userId") String userId,
                               @RequestBody EmailDTO emailDTO) throws UseException {
        return userService.updateEmail(userId, emailDTO.getEmail())
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

    @PatchMapping("/addFavorite/{userId}/{internshipId}")
    public boolean addFavorite(@PathVariable("userId") String userId,
                               @PathVariable("internshipId") String internshipId) throws UseException {
        return studentService.addFavorite(userId, internshipId);
    }

    @DeleteMapping("/removeFavorite/{userId}/{internshipId}")
    public void removeFavorite(@PathVariable("userId") String userId,
                               @PathVariable("internshipId") String internshipId) throws Exception {
        studentService.removeFavorite(userId, internshipId);
    }


    @GetMapping("/vacancyLists/{userId}")
    public List<InternshipAdvertDTO> getVacancyLists(@PathVariable("userId") String userId) throws UseException {
        return studentService.getVacancyLists(userId).map(InternshipAdvertEntityToDTO::getInternshipAdvertDTO).collect(Collectors.toList());
    }
    @GetMapping("/favorites/{userId}")
    public List<InternshipAdvertDTO> getFavoritesList(@PathVariable("userId") String userId){
        return studentService.getFavoritesList(userId).map(InternshipAdvertEntityToDTO::getInternshipAdvertDTO).collect(Collectors.toList());
    }

    private StudentDTO toStudentDTO(Student student) {
        User user = student.getUser();

        return new StudentDTO(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                user.getId(),
                user.getPassword(),
                user.getUsername(),
                student.getPhone(),
                (student.getLinkedInUrl() != "") ? student.getLinkedInUrl() : "",
                user.getRoles().stream().toList(),
                student.getSchoolName()
        );
    }

    private UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getRoles().stream().toList()
        );
    }


}
