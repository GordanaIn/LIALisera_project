package com.liserabackend.services;

import com.liserabackend.entity.InternshipAdvert;
import com.liserabackend.entity.repository.InternshipAdvertRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class InternshipAdvertService {
    private final InternshipAdvertRepository internshipVacancyRepository;
    public Stream<InternshipAdvert> getAllInternships() {
        return internshipVacancyRepository.findAll().stream();
    }
    public Optional<InternshipAdvert> getInternshipVacancy(String id) {
        return internshipVacancyRepository.findById(id);
    }

}
