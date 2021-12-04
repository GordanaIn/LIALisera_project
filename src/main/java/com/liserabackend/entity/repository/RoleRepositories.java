package com.liserabackend.entity.repository;

import com.liserabackend.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepositories extends JpaRepository<Role,String> {
        Role findByName(String name);
}