package com.liserabackend.entity.repository;

import com.liserabackend.entity.Company;
import com.liserabackend.enums.InternshipVacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipVacancyRepository extends JpaRepository<com.liserabackend.entity.InternshipVacancy, String> {
        List<com.liserabackend.entity.InternshipVacancy> findInternshipVacancyByTitle(String title);
        List<com.liserabackend.entity.InternshipVacancy> findInternshipVacancyByStatus(InternshipVacancy status);
}
