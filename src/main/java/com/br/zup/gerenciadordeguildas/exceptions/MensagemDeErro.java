package com.br.zup.gerenciadordeguildas.exceptions;

import lombok.*;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MensagemDeErro {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
}
