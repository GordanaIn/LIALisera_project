package com.liserabackend.entity.repository;

import com.liserabackend.entity.Match;
import com.liserabackend.entity.School;
import com.liserabackend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MatchRepository extends JpaRepository<Match,String> {

}
