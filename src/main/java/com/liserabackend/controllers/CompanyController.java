package com.liserabackend.controllers;

import com.liserabackend.dto.*;
import com.liserabackend.entity.Company;
import com.liserabackend.entity.User;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import com.liserabackend.services.CompanyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders ="*")
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyServiceImpl companyService;

    @GetMapping()
    public List<CompanyDTO> getCompanies() {
        return companyService.getCompanies()
                .map(this::toCompanyDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{userId}")
    public CompanyDTO getCompanyByUserId(@PathVariable("userId") String userId) throws UseException {
        return companyService.getCompanyByUserId(userId)
                .map(this::toCompanyDTO)
                .orElseThrow(()->new UseException(UseExceptionType.USER_NOT_FOUND));
    }

    @PostMapping("/addCompany")
    public ResponseEntity<?> addCompany(@RequestBody CreateCompany createCompany) throws UseException {
          return ResponseEntity.ok(companyService.addCompany(createCompany)
                  .map(this::toCompanyDTO));
    }
    @PatchMapping("/updateCompanyInfo")
    public CompanyDTO updateCompanyInfo(@RequestBody CreateCompany company) throws UseException {
        return companyService.updateCompanyInformation(company)
                .map(this::toCompanyDTO)
                .orElseThrow(() -> new UseException(UseExceptionType.COMPANY_NOT_FOUND));
    }


    private CompanyDTO toCompanyDTO(Company company) {
        User user=company.getUser();
        return new CompanyDTO(
                company.getId(),
                company.getName(),
                company.getOrgNumber(),
                company.getEmail(),
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().toString()
        );
    }

    /* Do we create a user(employee) profile page? */
    private UserDTO toUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole().toString()
        );
    }
}
