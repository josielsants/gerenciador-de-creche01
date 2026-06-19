package com.josielsants.gerenciadorcreche.repository;

import com.josielsants.gerenciadorcreche.model.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
