package com.liserabackend.services;

import com.liserabackend.entity.repository.RoleRepositories;
import com.liserabackend.dto.CreateStudent;
import com.liserabackend.entity.InternshipAdvert;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.InternshipAdvertRepository;
import com.liserabackend.entity.repository.StudentRepository;
import com.liserabackend.entity.repository.UserRepository;
import com.liserabackend.exceptions.UseException;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.liserabackend.exceptions.UseExceptionType.*;

@Service
@AllArgsConstructor
public class StudentService {
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final InternshipAdvertRepository internshipAdvertRepository;
    private final RoleRepositories roleRepositories;
    public Stream<Student> getStudents() {
       return studentRepository.findAll(Pageable.ofSize(1))
               .stream();
        //Remark-No need to filter by role since only student role is saved when object is created
       // return studentRepository.findAll()
         //       .stream()
           //     .filter(s -> s.getUser().getRole().equals(ROLE_STUDENT));
    }

    public Optional<Student> getStudentByUserId(String userId) throws UseException {
        final Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new UseException(USER_NOT_FOUND));
        return Optional.of(student);
    }

    public Optional<Student> getStudentByEmail(String username) throws UseException {
        final var role_student = roleRepositories.findAll().stream().filter(role -> role.getName().equals("ROLE_STUDENT")).findAny().get();
        final User user = userRepository.findByUsername(username).filter(u->u.getRoles().equals(role_student)).orElseThrow(() -> new UseException(USER_NOT_FOUND));

        final Student student = studentRepository.findByUserId(user.getId())
                .orElseThrow(() -> new UseException(STUDENT_NOT_FOUND));

        return Optional.of(student);
    }

    public Optional<Student> updateProfile(String userId, CreateStudent student) throws UseException {
        final var role_student = roleRepositories.findAll().stream().filter(role -> role.getName().equals("ROLE_STUDENT")).findAny().get();
        final var user = userRepository.findById(userId).filter(u->u.getRoles().equals(role_student)).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        final Student oldStudent = studentRepository.findByUserId(user.getId())
                .orElseThrow(() -> new UseException(STUDENT_NOT_FOUND));

        updateProfile(student, user, oldStudent);
        userRepository.save(user);
        studentRepository.save(oldStudent);
        return Optional.ofNullable(oldStudent);
    }

    private void updateProfile(CreateStudent student, User user, Student oldStudent) {
        user.setPassword(student.getPassword());
        user.setUsername(student.getEmail()); /** Email should not be modified */
        oldStudent.setFirstName(student.getFirstName());
        oldStudent.setLastName(student.getLastName());
        oldStudent.setPhone(student.getPhone());
        oldStudent.setUser(user);
    }

    public Student addInternshipToFavoritesList(String userId, String internshipId) throws UseException {
        final Student student = studentRepository.findByUserId(userId)
                .orElseThrow(() -> new UseException(USER_NOT_FOUND));

        InternshipAdvert internship = internshipAdvertRepository.getById(internshipId);
        student.getFavourites().add(internship);
        studentRepository.save(student);
        internshipAdvertRepository.save(internship);

        return student;
    }

    public Stream<InternshipAdvert> getFavoritesList(String userId) {
        return studentRepository.findByUserId(userId)
                .get()
                .getFavourites()
                .stream();
    }

    public boolean addFavorite(String userId, String internshipId) throws UseException {
        var student = studentRepository.findByUserId(userId).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        var internshipVacancy = internshipAdvertRepository.findById(internshipId).orElseThrow(() -> new UseException(INTERNSHIP_NOT_FOUND));
        student.getFavourites().add(internshipVacancy);
        studentRepository.save(student);
        return true;
    }

    public void removeFavorite(String userId, String internshipId) throws UseException {
        internshipAdvertRepository.findById(internshipId).orElseThrow(() -> new UseException(INTERNSHIP_NOT_FOUND));
        var student = studentRepository.findByUserId(userId).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        student.setFavourites(student.getFavourites()
                .stream()
                .filter(find -> !find.getId().equals(internshipId))
                .collect(Collectors.toSet()));
        studentRepository.save(student);
    }

    public Stream<InternshipAdvert> getVacancyLists(String userId) throws UseException {
        var student = studentRepository.findByUserId(userId).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        return student.getAppliedVacancies().stream();
    }
    public boolean applyInternship(String userId, String internshipId) throws UseException {
        Student student = getStudentByUserId(userId).get();
        final InternshipAdvert internshipVacancy = internshipAdvertRepository.findById(internshipId)
                .orElseThrow(() -> new UseException(INTERNSHIP_NOT_FOUND));
        if(student.getAppliedVacancies().contains(internshipVacancy))
            return false;

        student.getAppliedVacancies().add(internshipVacancy);
        studentRepository.save(student);
        return true;
    }



}
