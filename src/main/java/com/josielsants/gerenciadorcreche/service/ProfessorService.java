package com.josielsants.gerenciadorcreche.service;

import com.josielsants.gerenciadorcreche.exception.RecursoNaoEncontradoException;
import com.josielsants.gerenciadorcreche.model.Professor;
import com.josielsants.gerenciadorcreche.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessorService {

    private final ProfessorRepository repository;

    public ProfessorService(ProfessorRepository repository) {
        this.repository = repository;
    }

    public List<Professor> listarTodos() {
        return repository.findAll();
    }

    public Professor buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Professor", id));
    }

    public Professor criar(Professor professor) {
        return repository.save(professor);
    }

    public Professor atualizar(Long id, Professor dados) {
        Professor professor = buscarPorId(id);
        professor.setNome(dados.getNome());
        professor.setDisciplina(dados.getDisciplina());
        professor.setTurmaResponsavel(dados.getTurmaResponsavel());
        return repository.save(professor);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
