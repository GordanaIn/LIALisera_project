package com.liserabackend.services;

import com.liserabackend.dto.CreateCompany;
import com.liserabackend.entity.Company;
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
import java.util.stream.Stream;

import static com.liserabackend.enums.EnumRole.ROLE_EMPLOYER;
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



    public Optional<Company> addCompany(CreateCompany createCompany) throws UseException {
        //check if user found on user, then on company

        //need userId to create the company
        //there can be several users with the same name, can not throw on that
        //company can have many users, a user can only be employed at a company.

        if(userRepository.findByUsername(createCompany.getUsername()).isPresent())
            throw new UseException(UseExceptionType.USER_ALREADY_EXIST);

        User user=new User(createCompany.getUsername(), createCompany.getUserEmail(),createCompany.getPassword(), EnumRole.ROLE_EMPLOYER);
        user=userRepository.save(user);
        Company company=new Company(createCompany.getName(),createCompany.getOrgNumber(), createCompany.getCompanyEmail(), user);
        company=companyRepository.save(company);
        return  Optional.of(company);
    }

    public Optional<Company> updateCompanyProfile(String userId, CreateCompany company) throws UseException {
        final User oldUser = userRepository.findById(userId).filter(u->u.getRole().equals(ROLE_EMPLOYER)).orElseThrow(() -> new UseException(USER_NOT_FOUND));
        final Company oldCompany = companyRepository.findByUserId(oldUser.getId()).orElseThrow(() -> new UseException(COMPANY_NOT_FOUND));

        updateCompanyProfile(company, oldUser, oldCompany);
        userRepository.save(oldUser);
        companyRepository.save(oldCompany);
        return Optional.ofNullable(oldCompany);
    }

    private void updateCompanyProfile(CreateCompany company, User oldUser, Company oldCompany) {
        oldUser.setUsername((company.getUsername() == null) ? oldUser.getUsername() : company.getUsername());
        oldUser.setPassword((company.getPassword() == null) ? oldUser.getPassword() : company.getPassword());
        oldUser.setEmail((company.getUserEmail()== null) ? oldUser.getEmail() : company.getUserEmail());
        oldCompany.setName((company.getName()== null) ? oldCompany.getName() : company.getName());
        oldCompany.setOrgNumber((company.getOrgNumber()== null) ? oldCompany.getOrgNumber() : company.getOrgNumber());
        oldCompany.setEmail((company.getCompanyEmail()== null) ? oldCompany.getEmail() : company.getCompanyEmail());
        oldCompany.setUser(oldUser);
    }


    @Override
    public Stream<Company> getCompanies() {
        return companyRepository.findAll().stream();
    }

    public Optional<Company> getCompanyByUserId(String userId) throws UseException {
        return Optional.of(companyRepository.findByUserId(userId)
                .filter(user -> user.getUser().getRole().equals(ROLE_EMPLOYER))
                .orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND)));
    }



}
