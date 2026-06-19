package com.josielsants.gerenciadorcreche.controller;

import com.josielsants.gerenciadorcreche.model.Coordenador;
import com.josielsants.gerenciadorcreche.service.CoordenadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coordenadores")
public class CoordenadorController {

    private final CoordenadorService service;

    public CoordenadorController(CoordenadorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Coordenador> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Coordenador> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Coordenador> criar(@Valid @RequestBody Coordenador coordenador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.criar(coordenador));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Coordenador> atualizar(@PathVariable Long id, @Valid @RequestBody Coordenador coordenador) {
        return ResponseEntity.ok(service.atualizar(id, coordenador));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
