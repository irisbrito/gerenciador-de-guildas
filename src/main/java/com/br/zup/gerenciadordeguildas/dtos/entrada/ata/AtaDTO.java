package com.br.zup.gerenciadordeguildas.dtos.entrada.ata;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtaDTO {

    private Integer id;
    private LocalDate data;

    @NotNull
    @Size(max = 30, message = "Digitar no máximo 30 caracteres.")
    private String pauta;

    @NotNull
    @Size(max = 500, message = "Digitar no máximo 500 caracteres.")
    private String assuntos;

    private String guilda;

}
