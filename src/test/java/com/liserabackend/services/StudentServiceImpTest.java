/*
package com.liserabackend.services;

import com.liserabackend.dto.CreateStudent;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.StudentRepository;
import com.liserabackend.entity.repository.UserRepository;
import com.liserabackend.exceptions.UseException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentServiceImpTest {


    @Mock
    StudentRepository studentRepository;
    @Mock
    UserRepository userRepository;

    @InjectMocks
    StudentServiceImp studentServiceImp;


    @Test
    void test_saveStudent() {
        //Given
        Student student = new Student();
        when(studentRepository.save(student)).thenReturn(student);

        //When
        Student student1 = studentServiceImp.saveStudent(student);

        //Then
        assertEquals(student, student1);
    }

    @Test
    void test_addStudent() throws UseException {
        //Given
        CreateStudent createStudent = new CreateStudent("Mia", null, null, null, null, null, null);
        when(studentRepository.save(any())).thenAnswer(invocation -> invocation.getArgument(0));

        //When
        Optional<Student> student1 = studentServiceImp.addStudent(createStudent);

        //Then
        assertTrue(student1.isPresent());
        assertEquals(student1.get().getFirstName(), createStudent.getFirstName());

    }

    @Test
    void updateUsername() {
    }

    @Test
    void updatePassword() {
    }

    @Test
    void modifyPassword() {
    }

    @Test
    void getStudents() {
    }

    @Test
    void updateStudent() {
    }

    @Test
    void getAUserByUserName() {

        //When

    }

    @Test
    void getAUserByEmail() {
    }

    @Test
    void getStudentById() {
    }
}*/
