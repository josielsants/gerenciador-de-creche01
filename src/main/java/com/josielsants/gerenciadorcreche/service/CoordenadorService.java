package com.josielsants.gerenciadorcreche.service;

import com.josielsants.gerenciadorcreche.exception.RecursoNaoEncontradoException;
import com.josielsants.gerenciadorcreche.model.Coordenador;
import com.josielsants.gerenciadorcreche.repository.CoordenadorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoordenadorService {

    private final CoordenadorRepository repository;

    public CoordenadorService(CoordenadorRepository repository) {
        this.repository = repository;
    }

    public List<Coordenador> listarTodos() {
        return repository.findAll();
    }

    public Coordenador buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Coordenador", id));
    }

    public Coordenador criar(Coordenador coordenador) {
        return repository.save(coordenador);
    }

    public Coordenador atualizar(Long id, Coordenador dados) {
        Coordenador coordenador = buscarPorId(id);
        coordenador.setNome(dados.getNome());
        coordenador.setSetor(dados.getSetor());
        return repository.save(coordenador);
    }

    public void deletar(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }
}
