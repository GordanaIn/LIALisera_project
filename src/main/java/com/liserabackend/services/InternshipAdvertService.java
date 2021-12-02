package com.liserabackend.services;

import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.entity.repository.InternshipVacancyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class InternshipAdvertService {
    private final InternshipVacancyRepository internshipVacancyRepository;
    public Stream<InternshipVacancy> getAllInternships() {
        return internshipVacancyRepository.findAll().stream();
    }
    public Optional<InternshipVacancy> getInternshipVacancy(String id) {
        return internshipVacancyRepository.findById(id);
    }

}
