package com.liserabackend.services;

import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.exceptions.UseException;

import java.util.Optional;
import java.util.stream.Stream;

public interface IVacancyAdvert {
    Stream<InternshipVacancy> getAllInternships() ;
    Stream<InternshipVacancy> getFavorite() ;
    Optional<InternshipVacancy> updateInternship(String Id, InternshipVacancy internshipVacancy) throws UseException;
}
