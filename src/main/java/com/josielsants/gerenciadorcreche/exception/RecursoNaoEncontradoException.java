package com.josielsants.gerenciadorcreche.exception;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String entidade, Long id) {
        super(entidade + " com ID " + id + " não encontrado(a).");
    }
}
