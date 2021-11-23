package com.liserabackend.services;

import com.liserabackend.dto.CreateInternship;
import com.liserabackend.dto.StudentDTO;
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

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class InternshipVacancyServiceImp implements IVacancyAdvert {
    private InternshipVacancyRepository internshipVacancyRepository;
    private StudentRepository studentRepository;
    private UserRepository userRepository;
    private CompanyRepository companyRepository;
    public Stream<InternshipVacancy> getAllInternships() {
        return internshipVacancyRepository.findAll().stream();
    }

    @Override
    public Optional<InternshipVacancy> updateInternship(String Id, InternshipVacancy internship) throws UseException {
        InternshipVacancy oldInternship = internshipVacancyRepository.findById(Id).orElseThrow(() -> new UseException(UseExceptionType.INTERNSHIP_NOT_FOUND));
        updateInternship(oldInternship, internship);

        return Optional.of(oldInternship);
    }

    private InternshipVacancy updateInternship(InternshipVacancy oldInternship, InternshipVacancy internshipVacancy) {
        oldInternship.setContactEmployer(internshipVacancy.getContactEmployer());
        oldInternship.setTitle(internshipVacancy.getTitle());
        oldInternship.setDescription(internshipVacancy.getDescription());
        oldInternship.setStatus(internshipVacancy.getStatus());

        oldInternship.setTitle(internshipVacancy.getTitle());
        return oldInternship;
    }

    public boolean addToList(String id, String internshipId) {
        Optional<Student> student = studentRepository.findByUserId(id);
        if (student.isEmpty())
            return false;

        Optional<InternshipVacancy> internshipVacancy = internshipVacancyRepository.findById(internshipId);
        if (internshipVacancy.isEmpty())
            return false;
        student.get().getFavourites().add(internshipVacancy.get());
        studentRepository.save(student.get());
        return true;
    }

    public Optional<InternshipVacancy> getInternshipVacancy(String id) {
        return internshipVacancyRepository.findById(id);
    }

    public void deleteInternship(String id) throws UseException {
        if (internshipVacancyRepository.findAll()
                .stream()
                .noneMatch(t -> t.getId().equals(id)))
            throw new UseException(UseExceptionType.INTERNSHIP_NOT_FOUND);
        internshipVacancyRepository.delete(getInternshipVacancy(id).get());
    }

    public boolean addInternship(CreateInternship createInternship) {
        //find a userId
        Optional<User> user = userRepository.findById(createInternship.getUserId());
        if (user.isEmpty())
            return false;
        Optional<Company> company = companyRepository.findByUserId(user.get().getId());
        if (company.isEmpty())
            return false;
        InternshipVacancy internshipVacancy=new InternshipVacancy(createInternship.getTitle(), createInternship.getDescription(), createInternship.getDuration(), createInternship.getDatePosted(), createInternship.getContactPhone(),
                createInternship.getContactPhone(), company.get());

        if(company.get().getInternshipVacancyList()!=null)
            company.get().getInternshipVacancyList().add(internshipVacancy);
        //company.get().setInternshipVacancyList();
        companyRepository.save(company.get());
        internshipVacancyRepository.save(internshipVacancy);
       return true;
    }
}
