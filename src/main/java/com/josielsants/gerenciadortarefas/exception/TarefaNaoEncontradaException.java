package com.josielsants.gerenciadortarefas.exception;

public class TarefaNaoEncontradaException extends RuntimeException {

    public TarefaNaoEncontradaException(Long id) {
        super("Tarefa com ID " + id + " não encontrada.");
    }

    public TarefaNaoEncontradaException(String mensagem) {
        super(mensagem);
    }
}
