package com.liserabackend.services;

import com.liserabackend.dto.CreateInternship;
import com.liserabackend.entity.Company;
import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.CompanyRepository;
import com.liserabackend.entity.repository.InternshipVacancyRepository;
import com.liserabackend.entity.repository.StudentRepository;
import com.liserabackend.entity.repository.UserRepository;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import com.liserabackend.services.interfaces.IVacancyAdvert;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static com.liserabackend.exceptions.UseExceptionType.*;

@Service
@AllArgsConstructor
public class InternshipVacancyServiceImp implements IVacancyAdvert {
    private final InternshipVacancyRepository internshipVacancyRepository;
    private final StudentRepository studentRepository;
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;

    public Stream<InternshipVacancy> getAllInternships() {
        return internshipVacancyRepository.findAll().stream();
    }

    @Override
    public Optional<InternshipVacancy> updateInternship(String Id, InternshipVacancy internship) throws UseException {
        InternshipVacancy oldInternship = internshipVacancyRepository.findById(Id).orElseThrow(() -> new UseException(UseExceptionType.INTERNSHIP_NOT_FOUND));
        updateInternship(oldInternship, internship);

        return Optional.of(oldInternship);
    }

    public Optional<InternshipVacancy> getInternshipVacancy(String id) {
        return internshipVacancyRepository.findById(id);
    }

    public Optional<InternshipVacancy> addInternship(CreateInternship createInternship) throws UseException {
        final User user = userRepository.findById(createInternship.getUserId()).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        final Company company = companyRepository.findByUserId(user.getId()).orElseThrow(() -> new UseException(COMPANY_NOT_FOUND));

        return Optional.of(internshipVacancyRepository.save(new InternshipVacancy(createInternship.getTitle(), createInternship.getDescription(), createInternship.getDuration(), createInternship.getDatePosted(), createInternship.getEmployerName(),
                createInternship.getContactPhone(), createInternship.getRequiredNumber(), company)));
    }

    public void deleteInternship(String userId, String internshipId) throws UseException {
        final InternshipVacancy internshipVacancy = internshipVacancyRepository.findById(internshipId).orElseThrow(() -> new UseException(INTERNSHIP_NOT_FOUND));
        final Company company = companyRepository.findByUserId(userId).orElseThrow(() -> new UseException(COMPANY_NOT_FOUND));
        internshipVacancyRepository.delete(internshipVacancy);
        company.setInternshipVacancyList(company.getInternshipVacancyList()
                .stream()
                .filter(find -> !find.getId().equals(internshipId))
                .collect(Collectors.toSet()));
        companyRepository.save(company);
    }

    private InternshipVacancy updateInternship(InternshipVacancy oldInternship, InternshipVacancy internshipVacancy) {
        oldInternship.setContactEmployer(internshipVacancy.getContactEmployer());
        oldInternship.setTitle(internshipVacancy.getTitle());
        oldInternship.setDescription(internshipVacancy.getDescription());
        oldInternship.setStatus(internshipVacancy.getStatus());

        oldInternship.setTitle(internshipVacancy.getTitle());
        return oldInternship;
    }


    public boolean addFavorite(String userId, String internshipId) throws UseException {
        var student = studentRepository.findByUserId(userId).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        var internshipVacancy = internshipVacancyRepository.findById(internshipId).orElseThrow(() -> new UseException(INTERNSHIP_NOT_FOUND));
        student.getFavourites().add(internshipVacancy);
        studentRepository.save(student);
        return true;
    }

    public void removeFavorite(String userId, String internshipId) throws UseException {
        internshipVacancyRepository.findById(internshipId).orElseThrow(() -> new UseException(INTERNSHIP_NOT_FOUND));
        var student = studentRepository.findByUserId(userId).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        student.setFavourites(student.getFavourites()
                .stream()
                .filter(find -> !find.getId().equals(internshipId))
                .collect(Collectors.toSet()));
        studentRepository.save(student);
    }

    public Stream<InternshipVacancy> getVacancyLists(String userId) throws UseException {
        var student = studentRepository.findByUserId(userId).orElseThrow(() -> new UseException(USER_NOT_FOUND));
       // return internshipVacancyRepository.findAll().stream().filter(i -> i.getStudents().contains(student));
        return student.getAppliedVacancies().stream();
    }
}
