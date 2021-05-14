package com.br.zup.gerenciadordeguildas.exceptions;

public class TokenInvalidoException extends RuntimeException {

    public TokenInvalidoException() {
        super("Token não é válido!");
    }
}
