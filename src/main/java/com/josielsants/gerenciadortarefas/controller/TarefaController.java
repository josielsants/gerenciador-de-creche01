package com.josielsants.gerenciadortarefas.controller;

import com.josielsants.gerenciadortarefas.model.Tarefa;
import com.josielsants.gerenciadortarefas.service.TarefaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    private final TarefaService service;

    public TarefaController(TarefaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Tarefa>> listar() {
        List<Tarefa> tarefas = service.listarTodas();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tarefa> buscar(@PathVariable Long id) {
        Tarefa tarefa = service.buscarPorId(id);
        return ResponseEntity.ok(tarefa);
    }

    @PostMapping
    public ResponseEntity<Tarefa> criar(@Valid @RequestBody Tarefa tarefa) {
        Tarefa novaTarefa = service.criar(tarefa);
        return ResponseEntity.status(HttpStatus.CREATED).body(novaTarefa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarefa> atualizar(@PathVariable Long id, @Valid @RequestBody Tarefa tarefa) {
        Tarefa tarefaAtualizada = service.atualizar(id, tarefa);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/concluir")
    public ResponseEntity<Tarefa> concluir(@PathVariable Long id) {
        Tarefa tarefa = service.buscarPorId(id);
        tarefa.setConcluida(true);
        Tarefa tarefaAtualizada = service.atualizar(id, tarefa);
        return ResponseEntity.ok(tarefaAtualizada);
    }

    @PatchMapping("/{id}/reabrir")
    public ResponseEntity<Tarefa> reabrir(@PathVariable Long id) {
        Tarefa tarefa = service.buscarPorId(id);
        tarefa.setConcluida(false);
        Tarefa tarefaAtualizada = service.atualizar(id, tarefa);
        return ResponseEntity.ok(tarefaAtualizada);
    }
}
