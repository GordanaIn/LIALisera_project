package com.liserabackend.services;

import com.liserabackend.dto.CreateStudent;
import com.liserabackend.dto.ModifyPasswordDTO;
import com.liserabackend.entity.Education;
import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.EducationRepository;
import com.liserabackend.entity.repository.InternshipVacancyRepository;
import com.liserabackend.entity.repository.StudentRepository;
import com.liserabackend.entity.repository.UserRepository;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import com.liserabackend.services.interfaces.IStudent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static com.liserabackend.enums.EnumRole.ROLE_STUDENT;
import static com.liserabackend.exceptions.UseExceptionType.*;

@Service
@AllArgsConstructor
public class StudentServiceImp implements IStudent {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InternshipVacancyRepository internshipVacancyRepository;
    private final EducationRepository educationRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> updateEmail(String userId, String email) throws UseException {
        final User user = userRepository.findById(userId).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        user.setEmail(email);
        return Optional.ofNullable(saveUser(user));
    }

    @Override
    public Optional<User> updateUsername(String userId, String username) throws UseException {
        final User user = userRepository.findById(userId).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        user.setUsername(username);
        userRepository.save(user);
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> updatePassword(String userId, String password) throws UseException {
        final User user = userRepository.findById(userId).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        user.setPassword(password);
        return Optional.of(saveUser(user));
    }

    public Optional<User> modifyPassword(ModifyPasswordDTO modifyPasswordDTO) throws UseException {
        final User user = userRepository.findAll().stream()
                .filter(u -> u.getUsername().equals(modifyPasswordDTO.getUsername()))
                .filter(u -> u.getPassword().equals(modifyPasswordDTO.getCurrentPassword()))
                .findAny().orElseThrow(() -> new UseException(USER_NOT_FOUND));
        user.setPassword(modifyPasswordDTO.getNewPassword());
        userRepository.save(user);
        return Optional.of(saveUser(user));
    }

    @Override
    public Stream<Student> getStudents() {
        //Remark-No need to filter by role since only student role is saved when object is created
       return studentRepository.findAll().stream().filter(s -> s.getUser().getRole().equals(ROLE_STUDENT));
    }

    @Override
    public Optional<User> getUserByUserName(String username) throws UseException {
        final User user = userRepository.findByUsername(username).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        return Optional.ofNullable(user);
    }

    public Optional<User> getUserByEmail(String email) throws UseException {
        final User user = userRepository.findByEmail(email).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        return Optional.ofNullable(user);
    }

    public Optional<Student> getStudentById(String studentId) throws UseException {
        final Student student = studentRepository.findById(studentId).orElseThrow(() -> new UseException(STUDENT_NOT_FOUND));
        return Optional.ofNullable(student);
    }

    @Override
    public Optional<Student> getStudentByUserId(String userId) throws UseException {
        final Student student = studentRepository.findByUserId(userId).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        return Optional.of(student);
    }

    @Override
    public Optional<Student> getStudentByUserName(String username) throws UseException {
        final User user = userRepository.findByUsername(username).filter(u->u.getRole().equals(ROLE_STUDENT)).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        final Student student = studentRepository.findByUserId(user.getId()).orElseThrow(() -> new UseException(STUDENT_NOT_FOUND));
        return Optional.of(student);
    }

    public Optional<Student> updateProfile(String userId, CreateStudent student) throws UseException {
        final User user = userRepository.findById(userId).filter(u->u.getRole().equals(ROLE_STUDENT)).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        final Student oldStudent = studentRepository.findByUserId(user.getId()).orElseThrow(() -> new UseException(STUDENT_NOT_FOUND));
        updateProfile(student, user, oldStudent);
        return Optional.ofNullable(oldStudent);
    }

    public Student addInternshipToFavoritesList(String userId, String internshipId) throws UseException {
        final Student student = studentRepository.findByUserId(userId).orElseThrow(() -> new UseException(USER_NOT_FOUND));
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

    public Stream<Education> getStudentEducations(String userId) throws UseException {

        final Student student = studentRepository.findByUserId(userId)
                .filter(s -> s.getUser().getRole().equals(ROLE_STUDENT))
                .orElseThrow(() -> new UseException(USER_NOT_FOUND));
        if(student.getEducations().isEmpty())
            throw new UseException(STUDENT_EDUCTION_NOT_FOUND);

        return  student.getEducations().stream();

    }

    public Stream<Education> getEducations(String userId) {
        return  educationRepository.findAll().stream()
                .filter(education -> education.getUser().getId().equals(userId))
                .filter(education -> education.getUser().getRole().equals(ROLE_STUDENT));
    }

   @Override
    public Student saveStudent(Student student ) {
        return studentRepository.save(student);
    }
    public Optional<Student> addStudent(CreateStudent createStudent) throws UseException {
        // find if the same user is found
        if(userRepository.findByUsername(createStudent.getUsername()).isPresent())
            throw new UseException(UseExceptionType.USER_ALREADY_EXIST);

        User user=new User(createStudent.getUsername(), createStudent.getEmail(),createStudent.getPassword(), ROLE_STUDENT);
        user=saveUser(user);

        //get userId
      /*  String userId=userRepository.findByUsername(createStudent.getUsername()).get().getId();
        if(studentRepository.findByUserId(userId).isPresent())
            throw new UseException(UseExceptionType.USER_ALREADY_EXIST);*/

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

    public boolean applyInternship(String userId, String internshipId) throws UseException {
        final Student student = studentRepository.findByUserId(userId).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        System.out.println(student.getFirstName() + " "+student.getLastName());
        final InternshipVacancy internshipVacancy = internshipVacancyRepository.findById(internshipId).orElseThrow(() -> new UseException(INTERNSHIP_NOT_FOUND));
        System.out.println("Title "+internshipVacancy.getTitle() + " posted date  "+internshipVacancy.getDatePosted());
        Set<InternshipVacancy> internshipVacancies =new HashSet<>();
        Set<Student> students=new HashSet<>();
       /*if(student.getInternshipVacancies()==null){
           System.out.println("Hello if stud");
           internshipVacancies.add(internshipVacancy);
       }else{
           System.out.println("Hello else stud");
           student.getInternshipVacancies().add(internshipVacancy);
       }
        student.setInternshipVacancies(internshipVacancies);*/
        if(internshipVacancy.getStudents()==null){
            System.out.println("Hello if internship");
            students.add(student);
            internshipVacancy.setStudents(students);
        }else{
            System.out.println("Hello else internship");
            internshipVacancy.getStudents().add(student);
        }
        System.out.println("Hello Outside ");
        /*student.getInternshipVacancies().add(internshipVacancy);
        internshipVacancy.getStudents().add(student);*/
        studentRepository.save(student);
        internshipVacancyRepository.save(internshipVacancy);

        return true;
    }

    private void updateProfile(CreateStudent student, User user, Student oldStudent) {
        oldStudent.setFirstName(student.getFirstName());
        oldStudent.setLastName(student.getLastName());
        oldStudent.setPhone(student.getPhone());

        oldStudent.setUser(user);
    }

}
