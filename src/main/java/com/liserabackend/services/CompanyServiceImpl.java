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
}
