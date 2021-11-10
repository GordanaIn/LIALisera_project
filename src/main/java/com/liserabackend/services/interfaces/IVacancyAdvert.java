package com.liserabackend.services.interfaces;

import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.exceptions.UseException;

import java.util.Optional;
import java.util.stream.Stream;

public interface IVacancyAdvert {
    Stream<InternshipVacancy> getAllInternships() ;
    Optional<InternshipVacancy> updateInternship(String Id, InternshipVacancy internshipVacancy) throws UseException;
}
