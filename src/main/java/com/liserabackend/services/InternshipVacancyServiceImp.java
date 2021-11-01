package com.liserabackend.services;

import com.liserabackend.dto.CreateInternship;
import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.entity.repository.InternshipVacancyRepository;
import com.liserabackend.exceptions.UseException;
import com.liserabackend.exceptions.UseExceptionType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class InternshipVacancyServiceImp implements IVacancyAdvert {
    private InternshipVacancyRepository advertRepository;

    public Stream<InternshipVacancy> getAllInternships()  {
        return advertRepository.findAll().stream();
    }
    public Stream<InternshipVacancy> getFavorite(){
        return null;//advertRepository.findAll().stream().filter(fav->fav.getFavorite().equals(true));
    }

    @Override
    public Optional<InternshipVacancy> updateInternship(String Id, InternshipVacancy internship) throws UseException {
        InternshipVacancy oldInternship=advertRepository.findById(Id).orElseThrow( ()-> new UseException(UseExceptionType.INTERNSHIP_NOT_FOUND));
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
}
