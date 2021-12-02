package com.liserabackend.services;

import com.liserabackend.dto.CreateCompany;
import com.liserabackend.dto.CreateInternship;
import com.liserabackend.entity.Company;
import com.liserabackend.entity.InternshipAdvert;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.CompanyRepository;
import com.liserabackend.entity.repository.InternshipAdvertRepository;
import com.liserabackend.entity.repository.UserRepository;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.liserabackend.enums.EnumRole.ROLE_EMPLOYER;
import static com.liserabackend.exceptions.UseExceptionType.*;

@Service
@AllArgsConstructor
public class CompanyServiceImpl {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final InternshipAdvertRepository internshipAdvertRepository;
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Stream<Company> getCompanies() {
        return companyRepository.findAll().stream();
    }

    public Optional<Company> getCompanyByUserId(String userId) throws UseException {
        return Optional.of(companyRepository.findByUserId(userId)
                .filter(user -> user.getUser().getRole().equals(ROLE_EMPLOYER))
                .orElseThrow(() -> new UseException(UseExceptionType.USER_NOT_FOUND)));
    }

    public Optional<Company> addCompany(CreateCompany createCompany) throws UseException {
        final var user = userRepository.findById(createCompany.getUserId())
                .orElseThrow(() -> new UseException(USER_NOT_FOUND));

        Company company = new Company(createCompany.getName(),
                createCompany.getOrgNumber(),
                createCompany.getCompanyEmail(),
                user);
        return Optional.of(saveCompany(company));
    }

    public Optional<Company> updateCompanyInformation(CreateCompany company) throws UseException {
        final var oldCompany = getCompanyByUserId(company.getUserId()).get();

        oldCompany.setName((company.getName() == null) ? oldCompany.getName() : company.getName());
        oldCompany.setOrgNumber((company.getOrgNumber() == null) ? oldCompany.getOrgNumber() : company.getOrgNumber());
        oldCompany.setEmail((company.getCompanyEmail() == null) ? oldCompany.getEmail() : company.getCompanyEmail());

        return Optional.of(saveCompany(oldCompany));
    }
    public Optional<InternshipAdvert> addInternship(CreateInternship createInternship) throws UseException {
        final User user = userRepository.findById(createInternship.getUserId()).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        final Company company = companyRepository.findByUserId(user.getId()).orElseThrow(() -> new UseException(COMPANY_NOT_FOUND));

        return Optional.of(internshipAdvertRepository.save(new InternshipAdvert(createInternship.getTitle(), createInternship.getDescription(), createInternship.getDuration(), createInternship.getDatePosted(), createInternship.getEmployerName(),
                createInternship.getContactPhone(), createInternship.getRequiredNumber(), company)));
    }

    public void deleteInternship(String userId, String internshipId) throws UseException {
        final InternshipAdvert internshipVacancy = internshipAdvertRepository.findById(internshipId).orElseThrow(() -> new UseException(INTERNSHIP_NOT_FOUND));
        final Company company = companyRepository.findByUserId(userId).orElseThrow(() -> new UseException(COMPANY_NOT_FOUND));
        internshipAdvertRepository.delete(internshipVacancy);
        company.setInternshipVacancyList(company.getInternshipVacancyList()
                .stream()
                .filter(find -> !find.getId().equals(internshipId))
                .collect(Collectors.toSet()));
        companyRepository.save(company);
    }
    public Optional<InternshipAdvert> updateInternship(String Id, InternshipAdvert internship) throws UseException {
        InternshipAdvert oldInternship = internshipAdvertRepository.findById(Id).orElseThrow(() -> new UseException(INTERNSHIP_NOT_FOUND));
        updateInternship(oldInternship, internship);

        return Optional.of(oldInternship);
    }
    private InternshipAdvert updateInternship(InternshipAdvert oldInternship, InternshipAdvert internshipVacancy) {
        oldInternship.setContactEmployer(internshipVacancy.getContactEmployer());
        oldInternship.setTitle(internshipVacancy.getTitle());
        oldInternship.setDescription(internshipVacancy.getDescription());
        oldInternship.setStatus(internshipVacancy.getStatus());

        oldInternship.setTitle(internshipVacancy.getTitle());
        return oldInternship;
    }
}
