package com.liserabackend.entity.repository;

import com.liserabackend.entity.Education;
import com.liserabackend.enums.EducationType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EducationRepository extends JpaRepository<Education,String> {
    Optional<Education> findByTitle(String name);

}
