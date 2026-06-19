package com.josielsants.gerenciadorcreche.service;

import com.josielsants.gerenciadorcreche.exception.RecursoNaoEncontradoException;
import com.josielsants.gerenciadorcreche.model.Responsavel;
import com.josielsants.gerenciadorcreche.repository.ResponsavelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResponsavelService {

    private final ResponsavelRepository repository;

    public ResponsavelService(ResponsavelRepository repository) {
        this.repository = repository;
    }

    public List<Responsavel> listarTodos() {
        return repository.findAll();
    }

    public Responsavel buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Responsável", id));
    }

    public Responsavel criar(Responsavel responsavel) {
        return repository.save(responsavel);
    }

    public Responsavel atualizar(Long id, Responsavel dados) {
        Responsavel responsavel = buscarPorId(id);
        responsavel.setNome(dados.getNome());
        responsavel.setTelefone(dados.getTelefone());
        responsavel.setAlunoVinculado(dados.getAlunoVinculado());
        return repository.save(responsavel);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
