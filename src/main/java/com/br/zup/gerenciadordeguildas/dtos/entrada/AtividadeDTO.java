package com.br.zup.gerenciadordeguildas.dtos.entrada;

import com.br.zup.gerenciadordeguildas.enums.Status;
import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AtividadeDTO {

    private Integer id;

    @NotNull
    @Size(min = 2, max = 100, message = "Por favor digite um nome válido")
    private String nome;

    @NotNull
    @Size(max = 500, message = "Por favor, digite no máximo 500 caracteres")
    private String descricao;

    @NotNull
    @Size(min = 2, max = 100, message = "Por favor digite um nome válido")
    private String responsavel;

    @NotNull
    private Status status;
}
