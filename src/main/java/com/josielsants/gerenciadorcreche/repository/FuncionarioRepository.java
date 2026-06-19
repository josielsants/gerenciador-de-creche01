package com.josielsants.gerenciadorcreche.repository;

import com.josielsants.gerenciadorcreche.model.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
}
