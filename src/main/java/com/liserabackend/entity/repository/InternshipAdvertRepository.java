package com.liserabackend.entity.repository;

import com.liserabackend.entity.InternshipAdvert;
import com.liserabackend.enums.InternshipVacancyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InternshipAdvertRepository extends JpaRepository<InternshipAdvert, String> {
    List<InternshipAdvert> findInternshipVacancyByTitle(String title);
    List<InternshipAdvert> findInternshipVacancyByStatus(InternshipVacancyStatus status);
}
