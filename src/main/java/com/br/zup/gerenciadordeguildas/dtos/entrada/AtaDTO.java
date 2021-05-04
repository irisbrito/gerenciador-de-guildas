package com.br.zup.gerenciadordeguildas.dtos.entrada;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtaDTO {
    private Long id;

    @NotNull
    @Size(max = 30, message = "Digitar no máximo 30 caracteres.")
    private String titulo;

    @NotNull
    @Size(max = 500, message = "Digitar no máximo 500 caracteres.")
    private String assuntos;

    @NotNull
    @Size(max = 500, message = "Digitar no máximo 500 caracteres.")
    private String resultado;
}
