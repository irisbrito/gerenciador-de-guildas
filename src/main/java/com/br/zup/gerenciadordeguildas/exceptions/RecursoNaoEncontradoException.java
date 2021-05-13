package com.br.zup.gerenciadordeguildas.exceptions;

public class RecursoNaoEncontradoException extends RuntimeException {

    public RecursoNaoEncontradoException(String recurso, Integer id) {
        super(recurso + " não encontrado. ID: " + id);
    }

    public RecursoNaoEncontradoException(String recurso, String nome) {
        super(recurso + " não encontrado. Nome: " + nome);
    }
}
