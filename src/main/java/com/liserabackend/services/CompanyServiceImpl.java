package com.liserabackend.services;

import com.liserabackend.dto.CreatCompany;
import com.liserabackend.entity.Company;
import com.liserabackend.entity.repository.CompanyRepository;
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
    @Override
    public Company saveCompany(Company company) {
        return null;
    }

    @Override
    public Stream<Company> getCompanies() {
        return null;
    }

    @Override
    public Optional<Company> updateCompany(String companyId, Company company) throws UseException {
        return Optional.empty();
    }

    public Optional<Company> getCompanyByUserId(String userId) throws UseException {
        return Optional.of(companyRepository.findByUserId(userId).orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND)));
    }

    public Optional<Company> addCompany(CreatCompany createCompany) {
        return null;
    }
}
