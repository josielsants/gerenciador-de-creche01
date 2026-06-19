package com.josielsants.gerenciadorcreche.service;

import com.josielsants.gerenciadorcreche.exception.RecursoNaoEncontradoException;
import com.josielsants.gerenciadorcreche.model.Funcionario;
import com.josielsants.gerenciadorcreche.repository.FuncionarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    public List<Funcionario> listarTodos() {
        return repository.findAll();
    }

    public Funcionario buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário", id));
    }

    public Funcionario criar(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    public Funcionario atualizar(Long id, Funcionario dados) {
        Funcionario funcionario = buscarPorId(id);
        funcionario.setNome(dados.getNome());
        funcionario.setCargo(dados.getCargo());
        return repository.save(funcionario);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
