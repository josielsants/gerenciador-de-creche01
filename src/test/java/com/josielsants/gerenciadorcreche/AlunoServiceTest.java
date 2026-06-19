package com.josielsants.gerenciadorcreche;

import com.josielsants.gerenciadorcreche.exception.RecursoNaoEncontradoException;
import com.josielsants.gerenciadorcreche.model.Aluno;
import com.josielsants.gerenciadorcreche.repository.AlunoRepository;
import com.josielsants.gerenciadorcreche.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @Mock
    private AlunoRepository repository;

    @InjectMocks
    private AlunoService service;

    @Test
    void deveListarTodosOsAlunos() {
        Aluno a1 = new Aluno();
        a1.setId(1L);
        a1.setNome("João");
        a1.setIdade(4);
        a1.setTurma("Creche A");

        Aluno a2 = new Aluno();
        a2.setId(2L);
        a2.setNome("Maria");
        a2.setIdade(5);
        a2.setTurma("Creche B");

        when(repository.findAll()).thenReturn(List.of(a1, a2));

        List<Aluno> resultado = service.listarTodos();

        assertEquals(2, resultado.size());
        assertEquals("João", resultado.get(0).getNome());
        verify(repository, times(1)).findAll();
    }

    @Test
    void deveBuscarAlunoPorId() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Lucas");
        when(repository.findById(1L)).thenReturn(Optional.of(aluno));

        Aluno resultado = service.buscarPorId(1L);

        assertNotNull(resultado);
        assertEquals("Lucas", resultado.getNome());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoAlunoNaoExiste() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> service.buscarPorId(99L));
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void deveCriarAluno() {
        Aluno aluno = new Aluno();
        aluno.setNome("Ana");
        aluno.setIdade(3);
        aluno.setTurma("Creche A");
        aluno.setResponsavelNome("Paulo");

        when(repository.save(aluno)).thenReturn(aluno);

        Aluno resultado = service.criar(aluno);

        assertNotNull(resultado);
        assertEquals("Ana", resultado.getNome());
        verify(repository, times(1)).save(aluno);
    }

    @Test
    void deveCriarAlunoMesmoSemNomeQuandoORepositorioPermite() {
        Aluno aluno = new Aluno();
        aluno.setNome("");

        when(repository.save(aluno)).thenReturn(aluno);

        Aluno resultado = service.criar(aluno);

        assertNotNull(resultado);
        assertEquals("", resultado.getNome());
        verify(repository, times(1)).save(aluno);
    }

    @Test
    void deveAtualizarAluno() {
        Aluno alunoExistente = new Aluno();
        alunoExistente.setId(1L);
        alunoExistente.setNome("Pedro");
        alunoExistente.setIdade(4);
        alunoExistente.setTurma("Creche A");
        alunoExistente.setResponsavelNome("Maria");

        Aluno dadosAtualizados = new Aluno();
        dadosAtualizados.setNome("Pedro Silva");
        dadosAtualizados.setIdade(5);

        when(repository.findById(1L)).thenReturn(Optional.of(alunoExistente));
        when(repository.save(any(Aluno.class))).thenReturn(alunoExistente);

        Aluno resultado = service.atualizar(1L, dadosAtualizados);

        assertEquals("Pedro Silva", resultado.getNome());
        assertEquals(5, resultado.getIdade());
        verify(repository, times(1)).save(any(Aluno.class));
    }

    @Test
    void deveDeletarAluno() {
        Aluno aluno = new Aluno();
        aluno.setId(1L);
        aluno.setNome("Bruna");
        when(repository.findById(1L)).thenReturn(Optional.of(aluno));

        service.deletar(1L);

        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoDeletarAlunoInexistente() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(RecursoNaoEncontradoException.class, () -> service.deletar(99L));
        verify(repository, times(0)).deleteById(99L);
    }
}
