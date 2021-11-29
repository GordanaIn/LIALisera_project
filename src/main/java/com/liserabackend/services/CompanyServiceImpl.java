package com.liserabackend.services;

import com.liserabackend.dto.CreateCompany;
import com.liserabackend.dto.CreateStudent;
import com.liserabackend.entity.Company;
import com.liserabackend.entity.Student;
import com.liserabackend.entity.User;
import com.liserabackend.entity.repository.CompanyRepository;
import com.liserabackend.entity.repository.UserRepository;
import com.liserabackend.enums.EnumRole;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import com.liserabackend.services.interfaces.ICompany;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static com.liserabackend.enums.EnumRole.ROLE_EMPLOYER;
import static com.liserabackend.enums.EnumRole.ROLE_STUDENT;
import static com.liserabackend.exceptions.UseExceptionType.*;

@Service
@AllArgsConstructor
public class CompanyServiceImpl implements ICompany {
    private final CompanyRepository companyRepository;
    private final UserRepository userRepository;
    @Override
    public Company saveCompany(Company company) {
        return null;
    }

    @Override
    public Stream<Company> getCompanies() {
        return companyRepository.findAll().stream();
    }

    @Override
    public Optional<Company> updateCompany(String companyId, Company company)  {
        return Optional.empty();
    }

    public Optional<Company> getCompanyByUserId(String userId) throws UseException {
        return Optional.of(companyRepository.findByUserId(userId).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND)));
    }

    public Optional<Company> addCompany(CreateCompany createCompany) throws UseException {
        //check if user found on user, then on company
        if(userRepository.findByUsername(createCompany.getUsername()).isPresent())
            throw new UseException(UseExceptionType.USER_ALREADY_EXIST);
        User user=new User(createCompany.getUsername(), createCompany.getUserEmail(),createCompany.getPassword(), EnumRole.ROLE_EMPLOYER);
        user=userRepository.save(user);
        Company company=new Company(createCompany.getName(),createCompany.getOrganizationNumber(), createCompany.getCompanyEmail(), user);
        company=companyRepository.save(company);
        return  Optional.of(company);
    }
    public Optional<Company> updateProfile(String userId, CreateCompany company) throws UseException {
        final User user = userRepository.findById(userId).filter(u->u.getRole().equals(ROLE_EMPLOYER)).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        final Company oldCompany = companyRepository.findByUserId(user.getId()).orElseThrow(() -> new UseException(COMPANY_NOT_FOUND));

        updateProfile(company, user, oldCompany);
        userRepository.save(user);
        companyRepository.save(oldCompany);
        return Optional.ofNullable(oldCompany);
    }
    private void updateProfile(CreateCompany company, User user, Company oldCompany) {
        user.setUsername(company.getUsername());
        user.setPassword(company.getPassword());
        user.setEmail(company.getUserEmail());
        oldCompany.setName(company.getName());
        oldCompany.setOrgNumber(company.getOrganizationNumber());
        oldCompany.setEmail(company.getCompanyEmail());
        oldCompany.setUser(user);
    }

}
