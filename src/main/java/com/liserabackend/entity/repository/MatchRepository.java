package com.liserabackend.entity.repository;

import com.liserabackend.entity.Match;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchRepository extends JpaRepository<Match,String> {
}
