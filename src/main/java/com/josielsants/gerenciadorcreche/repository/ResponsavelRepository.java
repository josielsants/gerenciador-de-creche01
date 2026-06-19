package com.josielsants.gerenciadorcreche.repository;

import com.josielsants.gerenciadorcreche.model.Responsavel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ResponsavelRepository extends JpaRepository<Responsavel, Long> {
}
