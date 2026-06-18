package com.josielsants.gerenciadortarefas.service;

import com.josielsants.gerenciadortarefas.exception.TarefaNaoEncontradaException;
import com.josielsants.gerenciadortarefas.model.Tarefa;
import com.josielsants.gerenciadortarefas.repository.TarefaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TarefaService {

    private final TarefaRepository repository;

    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    public Tarefa buscarPorId(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID não pode ser nulo ou menor que zero");
        }
        return repository.findById(id)
                .orElseThrow(() -> new TarefaNaoEncontradaException(id));
    }

    public Tarefa criar(Tarefa tarefa) {
        if (tarefa == null) {
            throw new IllegalArgumentException("Tarefa não pode ser nula");
        }
        if (tarefa.getTitulo() == null || tarefa.getTitulo().isBlank()) {
            throw new IllegalArgumentException("Título da tarefa é obrigatório");
        }
        return repository.save(tarefa);
    }

    public Tarefa atualizar(Long id, Tarefa dados) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID não pode ser nulo ou menor que zero");
        }
        if (dados == null) {
            throw new IllegalArgumentException("Dados da tarefa não podem ser nulos");
        }

        Tarefa tarefa = buscarPorId(id);
        
        if (dados.getTitulo() != null && !dados.getTitulo().isBlank()) {
            tarefa.setTitulo(dados.getTitulo());
        }
        
        if (dados.getDescricao() != null) {
            tarefa.setDescricao(dados.getDescricao());
        }
        
        tarefa.setConcluida(dados.isConcluida());
        return repository.save(tarefa);
    }

    public void deletar(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("ID não pode ser nulo ou menor que zero");
        }
        buscarPorId(id);
        repository.deleteById(id);
    }
}
