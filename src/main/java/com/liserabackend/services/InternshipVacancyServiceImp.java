package com.liserabackend.services;

import com.liserabackend.dto.CreateInternship;
import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.entity.repository.InternshipVacancyRepository;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import com.liserabackend.services.interfaces.IVacancyAdvert;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class InternshipVacancyServiceImp implements IVacancyAdvert {
    private InternshipVacancyRepository internshipVacancyRepository;

    public Stream<InternshipVacancy> getAllInternships()  {
        return internshipVacancyRepository.findAll().stream();
    }

    @Override
    public Optional<InternshipVacancy> updateInternship(String Id, InternshipVacancy internship) throws UseException {
        InternshipVacancy oldInternship= internshipVacancyRepository.findById(Id).orElseThrow( ()-> new UseException(UseExceptionType.INTERNSHIP_NOT_FOUND));
        updateInternship(oldInternship, internship);

        return Optional.of(oldInternship);
    }

    private InternshipVacancy updateInternship(InternshipVacancy oldInternship, InternshipVacancy internshipVacancy){
        oldInternship.setContactEmployer(internshipVacancy.getContactEmployer());
        oldInternship.setTitle(internshipVacancy.getTitle());
        oldInternship.setDescription(internshipVacancy.getDescription());
        oldInternship.setStatus(internshipVacancy.getStatus());

        oldInternship.setTitle(internshipVacancy.getTitle());
        return oldInternship;
    }

    public Optional<InternshipVacancy> addToList(String id, CreateInternship internship) {
        return null;
    }

    public Optional<InternshipVacancy> getInternshipVacancy(String id) {
        return internshipVacancyRepository.findById(id);
    }
}
