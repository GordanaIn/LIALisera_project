package com.liserabackend.services;

import com.liserabackend.dto.CreateStudent;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.exceptions.UseException;
import java.util.Optional;
import java.util.stream.Stream;


public interface IStudent extends IUser {

    Student saveStudent(Student student);
    Stream<Student> getStudents();
    Optional<Student> updateStudent(String studentId, Student student) throws UseException;
    Optional<Student> getStudentByUserId(String userId) throws UseException;
    Optional<Student> getStudentByUserName(String username) throws UseException;

}
