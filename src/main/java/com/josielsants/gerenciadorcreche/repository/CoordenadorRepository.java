package com.josielsants.gerenciadorcreche.repository;

import com.josielsants.gerenciadorcreche.model.Coordenador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoordenadorRepository extends JpaRepository<Coordenador, Long> {
}
