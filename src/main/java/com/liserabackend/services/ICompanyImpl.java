package com.liserabackend.services;

import com.liserabackend.entity.Company;
import com.liserabackend.entity.repository.CompanyRepository;
import com.liserabackend.exceptions.UseException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;
@Service
@AllArgsConstructor
public class ICompanyImpl implements ICompany {
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

    public Optional<Company> getCompanyById(String companyId) throws UseException {
        return Optional.empty();
    }
}
