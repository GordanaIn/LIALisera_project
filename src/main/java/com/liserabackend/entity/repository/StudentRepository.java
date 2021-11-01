package com.liserabackend.entity.repository;

import com.liserabackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByUserId(String userId);
    Optional<Student> findByLastName(String lastname);
    Optional<Student> findByPhone(String phone);
}
