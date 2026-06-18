package com.josielsants.gerenciadortarefas;

import com.josielsants.gerenciadortarefas.exception.TarefaNaoEncontradaException;
import com.josielsants.gerenciadortarefas.model.Tarefa;
import com.josielsants.gerenciadortarefas.repository.TarefaRepository;
import com.josielsants.gerenciadortarefas.service.TarefaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TarefaServiceTest {

    @Mock
    private TarefaRepository repository;

    @InjectMocks
    private TarefaService service;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deveListarTodasAsTarefas() {
        // Arrange
        Tarefa t1 = new Tarefa();
        t1.setId(1L);
        t1.setTitulo("Estudar Spring Boot");
        t1.setConcluida(false);

        Tarefa t2 = new Tarefa();
        t2.setId(2L);
        t2.setTitulo("Fazer projeto");
        t2.setConcluida(false);

        when(repository.findAll()).thenReturn(List.of(t1, t2));

        // Act
        List<Tarefa> resultado = service.listarTodas();

        // Assert
        assertEquals(2, resultado.size());
        assertEquals("Estudar Spring Boot", resultado.get(0).getTitulo());
        verify(repository, times(1)).findAll();
    }

    @Test
    void deveBuscarTarefaPorId() {
        // Arrange
        Tarefa t = new Tarefa();
        t.setId(1L);
        t.setTitulo("Tarefa 1");
        when(repository.findById(1L)).thenReturn(Optional.of(t));

        // Act
        Tarefa resultado = service.buscarPorId(1L);

        // Assert
        assertNotNull(resultado);
        assertEquals("Tarefa 1", resultado.getTitulo());
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoQuandoTarefaNaoExiste() {
        // Arrange
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TarefaNaoEncontradaException.class, () -> service.buscarPorId(99L));
        verify(repository, times(1)).findById(99L);
    }

    @Test
    void deveCriarTarefa() {
        // Arrange
        Tarefa t = new Tarefa();
        t.setTitulo("Nova tarefa");
        t.setDescricao("Descrição");
        when(repository.save(t)).thenReturn(t);

        // Act
        Tarefa resultado = service.criar(t);

        // Assert
        assertNotNull(resultado);
        assertEquals("Nova tarefa", resultado.getTitulo());
        verify(repository, times(1)).save(t);
    }

    @Test
    void deveLancarExcecaoAoCriarTarefaSemTitulo() {
        // Arrange
        Tarefa t = new Tarefa();
        t.setTitulo("");

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.criar(t));
        verify(repository, times(0)).save(t);
    }

    @Test
    void deveAtualizarTarefa() {
        // Arrange
        Tarefa tarefaExistente = new Tarefa();
        tarefaExistente.setId(1L);
        tarefaExistente.setTitulo("Tarefa antiga");
        tarefaExistente.setConcluida(false);

        Tarefa tarefaAtualizada = new Tarefa();
        tarefaAtualizada.setTitulo("Tarefa nova");
        tarefaAtualizada.setConcluida(true);

        when(repository.findById(1L)).thenReturn(Optional.of(tarefaExistente));
        when(repository.save(any(Tarefa.class))).thenReturn(tarefaExistente);

        // Act
        Tarefa resultado = service.atualizar(1L, tarefaAtualizada);

        // Assert
        assertEquals("Tarefa nova", resultado.getTitulo());
        assertTrue(resultado.isConcluida());
        verify(repository, times(1)).save(any(Tarefa.class));
    }

    @Test
    void deveDeletarTarefa() {
        // Arrange
        Tarefa t = new Tarefa();
        t.setId(1L);
        t.setTitulo("Tarefa para deletar");
        when(repository.findById(1L)).thenReturn(Optional.of(t));

        // Act
        service.deletar(1L);

        // Assert
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoDeletarTarefaInexistente() {
        // Arrange
        when(repository.findById(99L)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(TarefaNaoEncontradaException.class, () -> service.deletar(99L));
        verify(repository, times(0)).deleteById(99L);
    }
}
