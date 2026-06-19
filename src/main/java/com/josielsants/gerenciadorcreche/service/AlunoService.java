package com.josielsants.gerenciadorcreche.service;

import com.josielsants.gerenciadorcreche.exception.RecursoNaoEncontradoException;
import com.josielsants.gerenciadorcreche.model.Aluno;
import com.josielsants.gerenciadorcreche.repository.AlunoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlunoService {

    private final AlunoRepository repository;

    public AlunoService(AlunoRepository repository) {
        this.repository = repository;
    }

    public List<Aluno> listarTodos() {
        return repository.findAll();
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Aluno", id));
    }

    public Aluno criar(Aluno aluno) {
        return repository.save(aluno);
    }

    public Aluno atualizar(Long id, Aluno dados) {
        Aluno aluno = buscarPorId(id);
        aluno.setNome(dados.getNome());
        aluno.setIdade(dados.getIdade());
        aluno.setTurma(dados.getTurma());
        aluno.setResponsavelNome(dados.getResponsavelNome());
        return repository.save(aluno);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
