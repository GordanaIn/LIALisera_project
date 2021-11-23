package com.liserabackend.entity.repository;

import com.liserabackend.entity.School;
import com.liserabackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface SchoolRepository extends JpaRepository<School, String> {
    Optional<School> findById(String id);
    Optional<School> findByName(String name);
    Optional<School> findByUserId(String userId);
}
