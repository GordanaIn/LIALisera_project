package com.liserabackend.entity.repository;

import com.liserabackend.entity.Student;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, String> {
    Optional<Student> findByUserId(String userId);

    Optional<Student> findByLastName(String lastname);

    Optional<Student> findByPhone(String phone);

    @Override
    <S extends Student> Page<S> findAll(Example<S> example, Pageable pageable);

}
