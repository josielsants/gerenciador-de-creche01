package com.josielsants.gerenciadorcreche.controller;

import com.josielsants.gerenciadorcreche.model.Responsavel;
import com.josielsants.gerenciadorcreche.service.ResponsavelService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsaveis")
public class ResponsavelController {

    private final ResponsavelService service;

    public ResponsavelController(ResponsavelService service) {
        this.service = service;
    }

    @GetMapping
    public List<Responsavel> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Responsavel> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Responsavel> criar(@Valid @RequestBody Responsavel responsavel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(responsavel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Responsavel> atualizar(@PathVariable Long id, @Valid @RequestBody Responsavel responsavel) {
        return ResponseEntity.ok(service.atualizar(id, responsavel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
