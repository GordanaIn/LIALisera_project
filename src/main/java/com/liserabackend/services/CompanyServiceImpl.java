package com.liserabackend.services;

import com.liserabackend.entity.repository.RoleRepositories;
import com.liserabackend.dto.CreateCompany;
import com.liserabackend.dto.CreateInternship;
import com.liserabackend.entity.Company;
import com.liserabackend.entity.InternshipAdvert;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.CompanyRepository;
import com.liserabackend.entity.repository.EmployeeRepository;
import com.liserabackend.entity.repository.InternshipAdvertRepository;
import com.liserabackend.entity.repository.UserRepository;
import com.liserabackend.exceptions.UseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.liserabackend.exceptions.UseExceptionType.*;

@Service
@AllArgsConstructor
public class CompanyServiceImpl {
    private final EmployeeRepository employeeRepository;
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    private final InternshipAdvertRepository internshipAdvertRepository;
    private final RoleRepositories roleRepositories;
    public Company saveCompany(Company company) {
        return companyRepository.save(company);
    }

    public Stream<Company> getCompanies() {
        return companyRepository.findAll().stream();
    }

    public Company getCompanyByEmployeeUserId(String userId) throws UseException {

        final var role_employee = roleRepositories.findAll().stream().filter(role -> role.getName().equals("ROLE_EMPLOYEE")).findAny().get();
        final var employee = employeeRepository.findByUserId(userId).filter(user -> user.getUser().getRoles().equals(role_employee)).orElseThrow(() -> new UseException(EMPLOYEE_NOT_FOUND));
        return companyRepository.findAll().stream().filter(company -> company.getEmployees().contains(employee)).findAny().get();
    }

    public Optional<Company> addCompany(CreateCompany createCompany) throws UseException {
        final var user = userRepository.findById(createCompany.getUserId())
                .orElseThrow(() -> new UseException(USER_NOT_FOUND));
        final var employee = employeeRepository.findByUserId(user.getId())
                .orElseThrow(() -> new UseException(EMPLOYEE_NOT_FOUND));

        Company company = new Company(createCompany.getName(),
                createCompany.getOrgNumber());
        company.getEmployees().add(employee);
        return Optional.of(saveCompany(company));
    }

    public Optional<Company> updateCompanyInformation(CreateCompany company) throws UseException {

        final var oldCompany  = getCompanyByEmployeeUserId(company.getUserId());
        oldCompany.setName((company.getName() == null) ? oldCompany.getName() : company.getName());
        oldCompany.setOrgNumber((company.getOrgNumber() == null) ? oldCompany.getOrgNumber() : company.getOrgNumber());
        return Optional.of(saveCompany(oldCompany));
    }
    public Optional<InternshipAdvert> addInternship(CreateInternship createInternship) throws UseException {
        final User user = userRepository.findById(createInternship.getUserId()).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        final Company company = getCompanyByEmployeeUserId(user.getId());
        return Optional.of(internshipAdvertRepository.save(new InternshipAdvert(createInternship.getTitle(), createInternship.getDescription(), createInternship.getDuration(), createInternship.getDatePosted(), createInternship.getEmployerName(),
                createInternship.getContactPhone(), createInternship.getRequiredNumber(), company)));
    }

    public void deleteInternship(String userId, String internshipId) throws UseException {
        final InternshipAdvert internshipVacancy = internshipAdvertRepository.findById(internshipId).orElseThrow(() -> new UseException(INTERNSHIP_NOT_FOUND));
        final Company company = getCompanyByEmployeeUserId(userId);
        internshipAdvertRepository.delete(internshipVacancy);
        company.setInternshipAdvertList(company.getInternshipAdvertList()
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
