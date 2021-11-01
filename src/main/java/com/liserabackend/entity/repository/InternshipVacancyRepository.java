package com.liserabackend.entity.repository;

import com.liserabackend.entity.InternshipVacancy;
import com.liserabackend.enums.AdvertStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipVacancyRepository extends JpaRepository<InternshipVacancy, String> {
        List<InternshipVacancy> findByAdvertByTitle(String title);
        List<InternshipVacancy> findByAdvertStatus(AdvertStatus status);
}
