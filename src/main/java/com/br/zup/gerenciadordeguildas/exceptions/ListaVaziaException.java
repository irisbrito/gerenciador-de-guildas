package com.br.zup.gerenciadordeguildas.exceptions;

public class ListaVaziaException extends RuntimeException {
    public ListaVaziaException(String resource, Character genre) {
        super("Não há nenhum" + genre + " " + resource + " cadastrad" + genre);
    }
}
