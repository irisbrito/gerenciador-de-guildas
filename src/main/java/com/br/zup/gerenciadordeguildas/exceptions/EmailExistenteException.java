package com.br.zup.gerenciadordeguildas.exceptions;

public class EmailExistenteException extends RuntimeException {
    public EmailExistenteException() {
        super("Membro já cadastrado com este e-mail!");
    }
}
