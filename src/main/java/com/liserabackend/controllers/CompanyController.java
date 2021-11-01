package com.liserabackend.controllers;

import com.liserabackend.services.CompanyServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders ="*")
@RequestMapping("/api/company")
public class CompanyController {
    private final CompanyServiceImpl companyService;
}
