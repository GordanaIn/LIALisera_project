package com.liserabackend.services;

import com.liserabackend.dto.CreateStudent;
import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.InternshipVacancyRepository;
import com.liserabackend.entity.repository.StudentRepository;
import com.liserabackend.enums.EnumRole;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import com.liserabackend.entity.repository.UserRepository;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class StudentServiceImp implements IStudent {
    UserRepository userRepository;
    StudentRepository studentRepository;
    InternshipVacancyRepository internshipVacancyRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateEmail(String userId, String email) throws UseException {
        User user=Optional.ofNullable(userRepository.findById(userId).orElseThrow(()->new UseException(UseExceptionType.User_NOT_FOUND))).get();
        user.setEmail(email);
        return Optional.ofNullable(saveUser(user));
    }

    @Override
    public Optional<User> updateUsername(String userId, String username) throws UseException {
        User user=Optional.ofNullable(userRepository.findById(userId).orElseThrow(()->new UseException(UseExceptionType.User_NOT_FOUND))).get();
        user.setUsername(username);
        user=saveUser(user);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> updatePassword(String userId, String password) throws UseException {
        User user=Optional.ofNullable(userRepository.findById(userId).orElseThrow(()->new UseException(UseExceptionType.User_NOT_FOUND))).get();
        user.setPassword(password);
        return Optional.of(saveUser(user));
    }
    @Override
    public Student saveStudent(Student student ) {
        return studentRepository.save(student);
    } //StudentDTO


    @Override
    public Stream<Student> getStudents() {
        return studentRepository.findAll().stream();
    }

    @Override
    public Optional<Student> updateStudent(String studentId, Student student) throws UseException {
        return Optional.empty();
    }

    @Override
    public Optional<User> getAUserByUserName(String username) throws UseException {
        return Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(() -> new UseException(UseExceptionType.User_NOT_FOUND)));
    }
    public Optional<User> getAUserByEmail(String email) throws UseException {
        return Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(() -> new UseException(UseExceptionType.User_NOT_FOUND)));
    }

    public Optional<Student> getStudentById(String studentId) throws UseException {
        return Optional.ofNullable(studentRepository.findById(studentId).orElseThrow(()->new UseException(UseExceptionType.STUDENT_NOT_FOUND)));
    }

    @Override
    public Optional<Student> getStudentByUserId(String userId) throws UseException {
        return Optional.ofNullable(studentRepository.findByUserId(userId).orElseThrow(()->new UseException(UseExceptionType.User_NOT_FOUND)));
    }

    @Override
    public Optional<Student> getStudentByUserName(String username) throws UseException {
        return Optional.empty();
    }

    public Optional<User> getAUserById(String userId) throws UseException {
        return  Optional.ofNullable(userRepository.findById(userId).orElseThrow(()->new UseException(UseExceptionType.User_NOT_FOUND)));
    }

    public Optional<Student> updateProfile(String Id, CreateStudent student) throws UseException {
        //find student by username
        User user=Optional.ofNullable(userRepository.findByUsername(student.getUsername()).orElseThrow(()->new UseException(UseExceptionType.User_NOT_FOUND))).get();
        Student oldStudent=Optional.ofNullable(studentRepository.findByUserId(user.getId())).orElseThrow(()->new UseException(UseExceptionType.STUDENT_NOT_FOUND)).get();
        oldStudent.setFirstName(student.getFirstName());
        //set other properties too
        return Optional.ofNullable(oldStudent);
    }
    public Student addInternshipToFavoritesList(String userId, String internshipId) {
        Student student = studentRepository.findByUserId(userId).get();
        InternshipVacancy internship = internshipVacancyRepository.getById(internshipId);

        student.getFavourites().add(internship);
        studentRepository.save(student);

        internship.getStudents().add(student);
        internshipVacancyRepository.save(internship);

        return student;
    }

    public Stream<InternshipVacancy> getFavoritesList(String userId) {
        return studentRepository.findByUserId(userId).get().getFavourites().stream();
    }
    public Student addStudent(CreateStudent createStudent) throws UseException {
        // find if the same user is found
        if(userRepository.findByUsername(createStudent.getUsername()).isPresent())
            throw new UseException(UseExceptionType.USER_ALREADY_EXIST);

        User user=new User(createStudent.getUsername(), createStudent.getEmail(),createStudent.getPassword(), EnumRole.ROLE_STUDENT);
        saveUser(user);

        //get userId
        String userId=userRepository.findByUsername(createStudent.getUsername()).get().getId();
        if(studentRepository.findByUserId(userId).isPresent())
            throw new UseException(UseExceptionType.USER_ALREADY_EXIST);

        Student student= new Student(
                createStudent.getFirstName(),
                createStudent.getLastName(),
                createStudent.getPhone(),
                user
        );
        student=saveStudent(student);
        return student;
    }

}
