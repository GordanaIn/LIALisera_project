package com.liserabackend.services;

import com.liserabackend.dto.CreateStudent;
import com.liserabackend.dto.StudentDTO;
import com.liserabackend.dto.UserDTO;
import com.liserabackend.entity.Education;
import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.EducationRepository;
import com.liserabackend.entity.repository.InternshipVacancyRepository;
import com.liserabackend.entity.repository.StudentRepository;
import com.liserabackend.enums.EnumRole;
import com.liserabackend.enums.SearchStudentBy;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import com.liserabackend.entity.repository.UserRepository;
import com.liserabackend.services.interfaces.IStudent;
import lombok.AllArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class StudentServiceImp implements IStudent {
    UserRepository userRepository;
    StudentRepository studentRepository;
    InternshipVacancyRepository internshipVacancyRepository;
    EducationRepository educationRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateEmail(String userId, String email) throws UseException {
        User user=Optional.ofNullable(userRepository.findById(userId).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND))).get();
        user.setEmail(email);

        System.out.println(user);
        return Optional.ofNullable(saveUser(user));
    }

    @Override
    public Optional<User> updateUsername(String userId, String username) throws UseException {
        User user=Optional.ofNullable(userRepository.findById(userId).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND))).get();
        System.out.println(user);
        user.setUsername(username);
        user=saveUser(user);
        System.out.println(user.getUsername());
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> updatePassword(String userId, String password) throws UseException {
        User user=Optional.ofNullable(userRepository.findById(userId).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND))).get();
        user.setPassword(password);
        return Optional.of(saveUser(user));
    }

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
        return Optional.ofNullable(userRepository.findByUsername(username).orElseThrow(() -> new UseException(UseExceptionType.USER_NOT_FOUND)));
    }
    public Optional<User> getAUserByEmail(String email) throws UseException {
        return Optional.ofNullable(userRepository.findByEmail(email).orElseThrow(() -> new UseException(UseExceptionType.USER_NOT_FOUND)));
    }

    public Optional<Student> getStudentById(String studentId) throws UseException {
        return Optional.ofNullable(studentRepository.findById(studentId).orElseThrow(()->new UseException(UseExceptionType.STUDENT_NOT_FOUND)));
    }

    @Override
    public Optional<Student> getStudentByUserId(String userId) throws UseException {
        return Optional.of(studentRepository.findByUserId(userId).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND)));
    }

    @Override
    public Optional<Student> getStudentByUserName(String username)  {
        return Optional.empty();
    }
    public Optional<Student> updateProfile(String Id, CreateStudent student) throws UseException {
        //find student by username
        User user=Optional.ofNullable(userRepository.findByUsername(student.getUsername()).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND))).get();
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
   @Override
    public Student saveStudent(Student student ) {
        return studentRepository.save(student);
    }
    public Optional<Student> addStudent(CreateStudent createStudent) throws UseException {
        // find if the same user is found
        if(userRepository.findByUsername(createStudent.getUsername()).isPresent())
            throw new UseException(UseExceptionType.USER_ALREADY_EXIST);

        User user=new User(createStudent.getUsername(), createStudent.getEmail(),createStudent.getPassword(), EnumRole.ROLE_STUDENT);
        user=saveUser(user);

        //get userId
        String userId=userRepository.findByUsername(createStudent.getUsername()).get().getId();
        if(studentRepository.findByUserId(userId).isPresent())
            throw new UseException(UseExceptionType.USER_ALREADY_EXIST);

        Student student= new Student(
                createStudent.getFirstName(),
                createStudent.getLastName(),
                createStudent.getPhone(),
                user);
        student.setLinkedInUrl("");
        student=saveStudent(student);
        if(student!=null){
            Education education=new Education("", createStudent.getSchoolName(),user);
            educationRepository.save(education);
            return Optional.of(student);
        }
        throw new UseException(UseExceptionType.STUDENT_NOT_SAVED);
    }

    private Stream<Student> searchStudent(SearchStudentBy searchStudentBy) {
        return switch(searchStudentBy){
            case First_NAME-> studentRepository.findAll().stream().filter(s -> Boolean.parseBoolean(s.getFirstName()));

        };
    }

    public Stream<Education> getStudentEducations(String userId) throws UseException {
        //get studentId then search eduction by studentId
        return  studentRepository.findByUserId(userId).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND)).getEducations().stream();

    }

    public  Stream<User> updateUser(String userId, UserDTO userDTO) {
            return null;
    }

    public Stream<Education> getEducations(String userId) {
        return  educationRepository.findAll().stream().filter(education -> education.getUser().getId().equals(userId));
    }
}
