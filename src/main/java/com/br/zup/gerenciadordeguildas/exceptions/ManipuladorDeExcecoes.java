package com.br.zup.gerenciadordeguildas.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;

@RestControllerAdvice
public class ManipuladorDeExcecoes {

    @ExceptionHandler(ListaVaziaException.class)
    public ResponseEntity<MensagemDeErro> listaVaziaException(
            ListaVaziaException exception,
            HttpServletRequest request) {
        String error = "Resource Not Found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        MensagemDeErro err = new MensagemDeErro(
                Instant.now(),
                status.value(),
                error,
                exception.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
}
