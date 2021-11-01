package com.liserabackend.services;

import com.liserabackend.entity.Company;
import com.liserabackend.exceptions.UseException;

import java.util.Optional;
import java.util.stream.Stream;

public interface ICompany {
    Company saveCompany(Company company);
    Stream<Company> getCompanies();
    Optional<Company> updateCompany(String companyId, Company company) throws UseException;
}
